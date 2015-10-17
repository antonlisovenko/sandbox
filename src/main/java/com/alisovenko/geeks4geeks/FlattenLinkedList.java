package com.alisovenko.geeks4geeks;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/flatten-a-linked-list-with-next-and-child-pointers/
 *
 * @author alisovenko 08.01.15
 */
public class FlattenLinkedList {
    private static class Node {
        Node next;
        Node down;
        int key;
        public Node(Node other) {
            key = other.key;
        }

        private Node(int key) {
            this.key = key;
        }

        Node next(int key) {
            Node n = new Node(key);
            this.next = n;
            return n;
        }
        Node next(Node then) {
            this.next = then;
            return then;
        }

        Node down(int key) {
            Node n = new Node(key);
            this.down = n;
            return n;
        }

        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }

    public static Node flatten(Node n) {
        Queue<Node> queue = new LinkedList<>();

        Node next = n.next;
        Node root = new Node(n);
        Node r = root;

        while (next != null || !queue.isEmpty()) {
            // If we reached end of line - let's take enqueued node
            if (next == null) {
                next = queue.poll();
                if (next == null) {
                    break;
                }
            }
            r.next = new Node(next);
            r = r.next;

            // if next level is not initialized - try to init
            if (next.down != null) {
                queue.add(next.down);
            }

            next = next.next;
        }

        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node n1 = root.next(5);
        Node d1 = n1.down(8);
        Node n2 = n1.next(9).next(4);
        Node d2 = n2.down(1);
        d1.next(12).next(11).down(3).next(30).next(21);
        d2.next(15).next(19).next(81).down(5).down(45);

        Node flatten = flatten(root);

        System.out.println();
    }
}

package com.alisovenko.coderust.tree;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 *
 * Make siblings for all nodes in binary tree using only O(1) of memory (queues do not fit here!)
 *
 * @author alisovenko 29.11.14
 */
public class SiblingsPointers {
    private static void setSiblings(Node node) {
        Node current = node;
        Node last = node;

        while (current != null) {
            if (current.left != null) {
                last.next = current.left;
                last = current.left;
            }
            if (current.right != null) {
                last.next = current.right;
                last = current.right;
            }
            current = current.next;
        }
    }
    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        setSiblings(n);

        System.out.println(n);
    }
}

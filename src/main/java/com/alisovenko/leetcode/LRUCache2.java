package com.alisovenko.leetcode;

import java.util.HashMap;

/**
 * @author alisovenko
 *         2/3/16.
 */
public class LRUCache2 {
    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<Integer, Node> inner = new HashMap<>();

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        tail = new Node(null, null);
        head = new Node(null, null);
        tail.previous = head;
        head.next = tail;
    }

    public int get(int key) {
        Node n = inner.get(key);
        if (n == null) return -1;

        push2Top(n);
        return n.value;
    }

    public void set(int key, int value) {
        Node n;
        if (inner.containsKey(key)) {
            n = inner.get(key);
            n.value = value;
            push2Top(n);
        } else {
            n = new Node(key, value);
            inner.put(key, n);
        }
        push2Top(n);
        ensureCapacity();
    }
    private void push2Top(Node n) {
        if (n.next != null && n.previous != null) {
            n.previous.next = n.next;
            n.next.previous = n.previous;
        }

        n.next = head.next;
        n.previous = head;
        head.next.previous = n;
        head.next = n;
    }
    private void ensureCapacity(){
        while (inner.size() > capacity) {
            // remove the last element
            inner.remove(tail.previous.key);
            tail.previous.previous.next = tail;
            tail.previous = tail.previous.previous;
        }
    }

    private static class Node {
        Node previous;
        Node next;
        Integer key;
        Integer value;

        public Node(Integer key, Integer value) {
            this(key, value, null, null);
        }

        public Node(Integer key, Integer value, Node previous, Node next) {
            this.key = key;
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUCache2 l = new LRUCache2(2);
        l.set(2, 1);
        l.set(1, 1);
        System.out.println(l.get(2));
        l.set(4, 1);
        System.out.println(l.get(1));
        System.out.println(l.get(2));
    }
}

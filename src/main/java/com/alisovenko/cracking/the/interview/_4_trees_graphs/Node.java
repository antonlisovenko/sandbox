package com.alisovenko.cracking.the.interview._4_trees_graphs;

import com.google.common.base.Strings;

/**
 * @author alisovenko 24.08.14
 */

public class Node {
    public Node(String key) {
        this.key = key;
    }

    public Node() {
    }

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node left;
    public Node right;
    public String key;
    public String value;
    public int depth;

    public static void print(Node n, int padding) {
        if (n == null) {
            return;
        }
        String s = n.key + (n.left != null || n.right != null ? " -> ": "");
        System.out.println(Strings.padStart(s, s.length() + padding * 2, ' '));
        print(n.left, padding + 1);
        print(n.right, padding + 1);
    }

    @Override
    public String toString() {
        return key;
    }
}
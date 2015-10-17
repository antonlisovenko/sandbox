package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-all-possible-interpretations/
 *
 * @author alisovenko 31.03.15
 */
public class DecodeString {
    private static int OFFSET = 'a' - '1';

    public static void printAllSequences(String str) {
        Node root = new Node();

        List<Node> currentNodes = new ArrayList<>();
        List<Node> _1stLevel = new ArrayList<>();
        _1stLevel.add(root);
        List<Node> _2ndLevel = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            char single = tryDecode(str.charAt(i));
            if (single > 0) {
                appendNew(single, _1stLevel, currentNodes);
            }
            char doubl = tryDecode(str, i);
            if (doubl > 0) {
                appendNew(doubl, _2ndLevel, currentNodes);
            }
            _2ndLevel = _1stLevel;
            _1stLevel = currentNodes;
            currentNodes = new ArrayList<>();
        }

        preOrderTraversal(root);
    }

    private static void preOrderTraversal(Node n) {
        if (n == null) {
            return;
        }
        // For leaves we print the whole path to parent
        if (n.left == null && n.right == null) {
            printPath(n);
            System.out.println();
        }
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    private static void printPath(Node n) {
        if (n.parent == null) {
            System.out.print(n.c);
            return;
        }
        printPath(n.parent);
        System.out.print(n.c);
    }

    private static void appendNew(char c, List<Node> appendable, List<Node> current) {
        appendable.stream().forEach(n -> {
            Node p = n.add(c);
            current.add(p);
        });
    }

    private static char tryDecode(char c) {
        if (c >= '1' || c <= '9') {
            return (char)(c + OFFSET);
        }
        return 0;
    }

    private static char tryDecode(String s, int i) {
        if (i >0) {
            String res = s.substring(i - 1, i + 1);
            try {
                int p = Integer.parseInt(res);
                if (p > 9 && p < 27) {
                    return (char) ('a' + p);
                }
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    private static class Node {
        private char c;
        private Node left;
        private Node right;
        private Node parent;

        public Node(char c, Node parent) {
            this.c = c;
            this.parent = parent;
        }

        public Node() {

        }

        public Node add(char c) {
            Node n = new Node(c, this);
            if (left == null) {
                left = n;
            } else {
                right = n;
            }
            return n;
        }

        @Override
        public String toString() {
            return Character.toString(c);
        }
    }

    public static void main(String[] args) {
        printAllSequences("121");
        printAllSequences("77");
        printAllSequences("717");
        printAllSequences("1237821");
    }
}

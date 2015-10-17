package com.alisovenko.geeks4geeks;

/**
 * http://www.geeksforgeeks.org/find-all-possible-interpretations/
 *
 * @author alisovenko 31.03.15
 */
public class DecodeString2Recursive {
    private static int OFFSET = 'a' - '1';

    public static void fillTree(String str, int pointer, Node node) {
        if (str.length() <= pointer) {
            return;
        }

        char single = tryDecode(str.charAt(pointer));
        if (single > 0) {
            Node child = new Node(single, node);
            node.left = child;
            fillTree(str, pointer + 1, child);
        }
        char doubl = tryDecode(str, pointer);
        if (doubl > 0) {
            Node child = new Node(doubl, node);
            node.right = child;
            fillTree(str, pointer + 2, child);
        }
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

    private static char tryDecode(char c) {
        if (c >= '1' && c <= '9') {
            return (char) (c + OFFSET);
        }
        return 0;
    }

    private static char tryDecode(String s, int i) {
        if (i < s.length() - 1) {
            String res = s.substring(i, i + 2);
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

        public Node() { }

        @Override
        public String toString() {
            return Character.toString(c);
        }
    }

    public static void findAndDecode(String s) {
        Node root = new Node();
        fillTree(s, 0, root);

        preOrderTraversal(root);
        System.out.println();
    }

    public static void main(String[] args) {
        findAndDecode("121");
        findAndDecode("77");
        findAndDecode("717");
        findAndDecode("1237821");
        findAndDecode("12dfg12424");
    }
}

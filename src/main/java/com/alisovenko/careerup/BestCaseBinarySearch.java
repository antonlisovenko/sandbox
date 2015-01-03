package com.alisovenko.careerup;

/**
 * @author alisovenko 23.09.14
 */
public class BestCaseBinarySearch {
    private static class Node {
        private Node left;
        private Node right;
        private Node parent;
        private final int value;

        private Node(Node parent, int value) {
            this.parent = parent;
            this.value = value;
        }

        public void add(int value) {
            add(this, value);
        }

        public static void add(Node n, int value) {
            if (value <= n.value && n.left == null) {
                n.left = new Node(n, value);
            }
            else if (value > n.value && n.right == null) {
                n.right = new Node(n, value);
            }
            else if (value <= n.value) {
                add(n.left, value);
            }
            else {
                add(n.right, value);
            }
        }

        public static int bestCaseSearch(Node n, int value) {
            if (n.value == value) {
                return value;
            }
            if (value <= n.value && n.left == null) {
                int r = successor(n, true);
                int l = predessor(n, true);
                if (Math.abs(value - r) < Math.abs(value - l)) {
                    return r;
                }
                return l;
            }
            if (value > n.value && n.right == null) {
                int r = successor(n, false);
                int l = predessor(n, false);
                if (Math.abs(value - r) < Math.abs(value - l)) {
                    return r;
                }
                return l;
            }
            if (value <= n.value) {
                return bestCaseSearch(n.left, value);
            } else {
                return bestCaseSearch(n.right, value);
            }
        }


        private static int successor(Node n, boolean isLeft) {
            if (isLeft) {
                Node next = n.right;
                if (next == null) {
                    return n.value;
                }
                while (next.left != null) {
                    next = next.left;
                }
                return next.value;
            }
            Node next = n.parent;
            while (next != null) {
                if (next.left == n) {
                    return next.value;
                }
                n = next;
                next = next.parent;
            }
            return Integer.MAX_VALUE;
        }
        private static int predessor(Node n, boolean isLeft) {
            if (!isLeft) {
                Node next = n.left;
                if (next == null) {
                    return n.value;
                }
                while (next.right != null) {
                    next = next.right;
                }
                return next.value;
            }
            Node next = n.parent;
            while (next != null) {
                if (next.right == n) {
                    return next.value;
                }
                n = next;
                next = next.parent;
            }
            return Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(null, 7);
        root.add(4);
        root.add(1);
        root.add(5);
        root.add(8);
        root.add(12);
        root.add(9);

        assert Node.bestCaseSearch(root, 3) == 4 : Node.bestCaseSearch(root, 3);
        assert Node.bestCaseSearch(root, 10) == 9 : Node.bestCaseSearch(root, 10);
        assert Node.bestCaseSearch(root, 11) == 12 : Node.bestCaseSearch(root, 11);
    }

}

package com.alisovenko.cracking.the.interview._4_trees_graphs;

/**
 * @author alisovenko 30.08.14
 */
public class _4_5 {
    public static boolean isBinarySearchNode(Node node) {
        if (node == null) {
            return true;
        }
        if (!checkChildren(node)) {
            return false;
        }

        return isRightBinarySearch(node.right, node) && isLeftBinarySearch(node.left, node);
    }

    private static boolean isLeftBinarySearch(Node n, Node comparison) {
        if (n == null) {
            return true;
        }
        if (!checkChildren(n)) {
            return false;
        }
        if (n.right != null && n.right.key.compareTo(comparison.key) > 0) {
            return false;
        }
        return isLeftBinarySearch(n.right, comparison);
    }

    private static boolean isRightBinarySearch(Node n, Node comparison) {
        if (n == null) {
            return true;
        }
        if (!checkChildren(n)) {
            return false;
        }
        if (n.left != null && n.left.key.compareTo(comparison.key) <= 0) {
            return false;
        }
        return isRightBinarySearch(n.left, comparison);
    }

    private static boolean checkChildren(Node n) {
        if (n.left != null && n.left.key.compareTo(n.key) > 0 ){
            return false;
        }
        if (n.right != null && n.right.key.compareTo(n.key) < 0 ){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Node root = new Node("6");
        root.left = new Node("3");
        root.right = new Node("8");
        root.left.left = new Node("2");
        root.left.right = new Node("5");
        root.right.left = new Node("7");
        root.right.right = new Node("9");

        System.out.println(isBinarySearchNode(root));
    }
}

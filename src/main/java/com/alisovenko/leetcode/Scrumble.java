package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko 31.03.15
 */
public class Scrumble {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return false;
        }
        if (s2 == null || s2.length() == 0) {
            return false;
        }

        Node root = createTree(s2, 0, s2.length() - 1);

        char[] l = s1.toCharArray();

        return guess(l, root);
    }
    private boolean guess(char[] chars, Node n) {
        if (n == null) {
            return true;
        }
        if (chars.length == 1) {
            return chars[0] == n.key[0];
        }
        int med = (chars.length - 1) / 2;

        char[] left = Arrays.copyOfRange(chars, 0, med + 1);
        char[] right = Arrays.copyOfRange(chars, med + 1, chars.length);

        Arrays.sort(left);
        Arrays.sort(right);

        char[] nLeft = Arrays.copyOf(n.left.key, n.left.key.length);
        char[] nRight = Arrays.copyOf(n.right.key, n.right.key.length);

        Arrays.sort(nLeft);
        Arrays.sort(nRight);

        if (Arrays.equals(left, nLeft) && Arrays.equals(right, nRight)) {
            return guess(left, n.left) && guess(right, n.right);
        }
        else if (Arrays.equals(left, nRight) && Arrays.equals(right, nLeft)) {
            return guess(left, n.right) && guess(right, n.left);
        }
        return false;
    }
    private void scanLeafs(Node n, StringBuilder s) {
        if (n == null)
            return;
        // checking only leafs and appending them to stringBuilder
        if (n.left == null && n.right == null) {
            s.append(n.key);
        }
        scanLeafs(n.left, s);
        scanLeafs(n.right, s);
    }
    private void scramble(Node n) {
        if (n == null) {
            return;
        }
        // post order traversal
        scramble(n.left);
        scramble(n.right);

        Node t = n.left;
        n.left = n.right;
        n.right = t;
    }

    private Node createTree(String s, int start, int end) {
        if (start > end) {
            return null;
        }

        Node result = new Node(s.substring(start, end + 1).toCharArray());

        if (start < end) {
            int med = start + (end - start) / 2;
            result.left = createTree(s, start, med);
            result.right = createTree(s, med + 1, end);
        }

        return result;
    }

    private static class Node {
        char[] key;
        Node left;
        Node right;
        Node next;

        public Node(char[] k) {
            this.key = k;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Scrumble().isScramble("abb", "bba"));
    }
}

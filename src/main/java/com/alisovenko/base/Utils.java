package com.alisovenko.base;

import java.util.List;

/**
 * @author alisovenko 16.12.14
 */
public class Utils {

    private static int c= 0;

    public static void startFrame(String txt) {
        c++;
        pad();
        System.out.println(txt);
    }

    public static void endFrame(String txt) {
        c--;
        pad();
        System.out.println(txt);
    }
    private static void pad() {
        for (int i = 0; i < c; i++) {
            System.out.print('.');
        }
        System.out.print(" ");
    }

    public static void printMatrix(int[][] m) {
        for (final int[] ints : m) {
            for (final int i : ints) {
                System.out.printf("%2d", i);
            }
            System.out.println();
        }
    }
    public static void printMatrix(int[][] m, String rowHead, String colHead) {
        System.out.print("  ");

        for (final char c : rowHead.toCharArray()) {
            System.out.printf("%-2s", c);
        }
        System.out.println();

        int p = 0;
        for (final int[] ints : m) {
            System.out.printf("%-2s", colHead.charAt(p++));

            for (final int i : ints) {
                System.out.printf("%-2d", i);
            }
            System.out.println();
        }
    }

    public static TreeNode build(Integer[] integers, int pos) {
        if (pos >= integers.length) return null;
        if (integers[pos] == null) return null;

        TreeNode n = new TreeNode(integers[pos]);
        n.left = build(integers, pos * 2 + 1);
        n.right = build(integers, pos * 2 + 2);
        if (n.left != null && n.left.val > n.val || n.right != null && n.right.val < n.val){
            System.out.println("ERROR!");
        }

        return n;
    }
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

}

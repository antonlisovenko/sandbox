package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         7/1/16.
 */
public class LargestBstSubtree{
    private int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        max = 0;
        int size = traverse(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        max = Math.max(size, max);
        return max;
    }

    private int traverse(TreeNode root, int lo, int max) {
        if (root == null) return 0;

        int sizeLeft = traverse(root.left, lo, root.val);
        int sizeRight = traverse(root.right, root.val, max);

        max = Math.max(max, Math.max(sizeLeft, sizeRight));

        return (root.val <= lo || root.val >= max || sizeLeft < 0 || sizeRight < 0) ? -1 : sizeLeft + sizeRight + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.println(new LargestBstSubtree().largestBSTSubtree(root));
    }

    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
}

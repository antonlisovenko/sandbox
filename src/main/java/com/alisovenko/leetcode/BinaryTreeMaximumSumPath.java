package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         6/26/16.
 */
public class BinaryTreeMaximumSumPath {
    public int maxPathSum(TreeNode root) {
        int[] res = find(root);
        return Math.max(res[0], res[1]);
    }
    private int[] find(TreeNode node) {
        if (node == null) return null;
        int[] left = find(node.left);
        int[] right = find(node.right);
        if (left == null && right == null) return new int[]{node.val, node.val};
        if (left == null) return new int[]{Math.max(Math.max((right[1] + node.val), right[0]), node.val), Math.max(right[1] + node.val, node.val)};
        if (right == null) return new int[]{Math.max(Math.max((left[1] + node.val), left[0]), node.val), Math.max(left[1] + node.val, node.val)};

        return new int[]{Math.max(Math.max((left[1] + right[1] + node.val), Math.max(left[0], right[0])), node.val),
                Math.max(Math.max(left[1], right[1]) + node.val, node.val)};
    }

     public class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
         }
}

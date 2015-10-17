package com.alisovenko.leetcode;

/**
 * @author alisovenko 20.06.15
 */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = leftHeight(node);
        int r = rightHeight(node);
        if (l == r) {
            return (2 << l - 1) - 1;
        }
        return count(node.left) + count(node.right) + 1;
    }

    private int leftHeight(TreeNode node) {
        if (node == null)  {
            return 0;
        }
        return leftHeight(node.left) + 1;
    }


    private int rightHeight(TreeNode node) {
        if (node == null)  {
            return 0;
        }
        return rightHeight(node.right) + 1;
    }

    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
        }

    public static void main(String[] args) {
        System.out.println(new CountCompleteTreeNodes().countNodes(new TreeNode(1)));
    }
}
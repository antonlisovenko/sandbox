package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         4/3/16.
 */
public class BinaryTreeFromPreorderAndInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;

        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode build(int[] preorder, int[] inorder, int preLo, int preHi, int inLo, int inHi) {
        if (inLo > inHi) return null;

        // root of preorder subtree
        int h = preorder[preLo];
        TreeNode root = new TreeNode(h);

        // find the index of root in inorder array
        int k;
        for (k = inLo; k <= inHi; k++) {
            if (inorder[k] == h) break;
        }
        if (inorder[k] != h) throw new RuntimeException();
        int leftSubtreeSize = k - inLo;

        root.left = build(preorder, inorder, preLo + 1, preLo + leftSubtreeSize, inLo, k - 1);
        root.right = build(preorder, inorder, preLo + leftSubtreeSize + 1, preHi, k + 1, inHi);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static void main(String[] args) {
        new BinaryTreeFromPreorderAndInorder().buildTree(new int[]{1, 2}, new int[]{2, 1});
    }
}

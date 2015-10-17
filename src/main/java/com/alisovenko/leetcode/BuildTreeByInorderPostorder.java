package com.alisovenko.leetcode;

import com.alisovenko.base.Node;

/**
 * Having inorder and postorder sequences build the binary tree.
 *
 * Algorithm:
 * - iteravely find the root of next subtree (ipostorder iterator has the parent of subtree at the end of the subsequence)
 * - find the location of the root of subtree in inorder subsequence
 * - left subtree will have k - start - 1 nodes
 * - recurse, having k as the delimeter for inorder subsequence postorder subsequence start and end updated by the length of left/right subtrees
 *
 * @author alisovenko 07.12.14
 */
public class BuildTreeByInorderPostorder {
    public Node buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private Node build(int[] inorder, int[] postorder, int start, int end, int pStart, int pEnd) {
        if (end < start || pEnd < pStart) {
            return null;
        }
        int root = postorder[pEnd];
        Node rootNode = new Node(null, root);

        int k = 0;
        for (int i = 0; i <= end; i++) {
            if (inorder[i] == root) {
                k = i;
                break;
            }
        }

        // k - (start + 1) : length of the subtree [start, k) (excluding k)
        rootNode.left = build(inorder, postorder, start, k - 1, pStart, pStart + k - start -1);

        // k - start : length of the subtree [start, k] (including k)
        // decreasing the right border by 1!
        rootNode.right = build(inorder, postorder, k + 1, end, pStart + k - start, pEnd - 1);

        return rootNode;
    }

    public static void main(String[] args) {
        new BuildTreeByInorderPostorder().buildTree(new int[]{1, 3, 2}, new int[]{3, 2, 1}).print();
        new BuildTreeByInorderPostorder().buildTree(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 3, 2, 5, 7, 6, 4}).print();

    }
}

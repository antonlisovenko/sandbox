package com.alisovenko.leetcode;

import com.alisovenko.base.Utils;

/**
 * @author alisovenko
 *         2/19/16.
 */
public class BinaryTreeLongestConsequtivePath {
    private static int maxLength = 0;

    public int longestConsecutive(Utils.TreeNode root) {
        traverseAndFindLongest(root, null, 0);
        return maxLength;
    }

    private void traverseAndFindLongest(Utils.TreeNode n, Utils.TreeNode parent, int currPath) {
        if (n == null) return;
        if (parent == null || n.val - parent.val != 1) currPath = 1;
        else currPath++;

        maxLength = Math.max(maxLength, currPath);

        traverseAndFindLongest(n.left, n, currPath);
        traverseAndFindLongest(n.right, n, currPath);
    }

    public static void main(String[] args) {
        System.out.println(new BinaryTreeLongestConsequtivePath().longestConsecutive(DeseriallizeBTLevelOrder.deserialize("[2,null,3,2,null,1]")));
    }
}

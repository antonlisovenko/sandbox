package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         12/30/15.
 */
public class Codec {
    private static int idx;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }
    private void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append('#');
            return;
        }
        builder.append('*').append(root.val);
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        idx = 0;
        return doDeserialize(data);
    }

    private TreeNode doDeserialize(String data) {
        TreeNode root = readNode(data);

        if (root == null) return null;
        root.left = doDeserialize(data);
        root.right = doDeserialize(data);

        return root;
    }

    private TreeNode readNode(String data) {
        if (idx >= data.length() || data.charAt(idx) == '#') {
            idx++;
            return null;
        }
        assert data.charAt(idx) == '*';
        idx++;
        int from = idx;

        while (idx < data.length() && data.charAt(idx) != '*' && data.charAt(idx) != '#') {
            idx++;
        }
        return new TreeNode(Integer.parseInt(data.substring(from, idx)));
    }

    public static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        codec.deserialize(codec.serialize(new TreeNode(1)));
    }
}
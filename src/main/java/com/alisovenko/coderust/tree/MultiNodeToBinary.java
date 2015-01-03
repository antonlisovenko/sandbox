package com.alisovenko.coderust.tree;

import com.alisovenko.base.MultiNode;
import com.alisovenko.base.Node;

/**
 * Coderust: transform n-ary tree to binary and back.
 *
 * Main idea is to
 * @author alisovenko 29.11.14
 */
public class MultiNodeToBinary {
    /**
     * Input:
     *       1
     *   /   |   \
     *   2   3   4
     *     /  \
     *    5   6
     *
     * Output:
     *
     *              1
     *             /
     *            2
     *           /
     *          3
     *         / \
     *        4  5
     *            \
     *            6
     */
    private static Node multi2Binary(MultiNode root) {
        return convert2Binary(root, false);
    }

    private static Node convert2Binary(MultiNode node, boolean right) {
        if (node == null) {
            return null;
        }
        Node n = new Node(null, node.key);

        if (node.children.size() > 0) {
            Node p = n;
            for (final MultiNode child : node.children) {
                if (right) {
                    p.right = convert2Binary(child, !right);
                    p = p.right;
                }
                else {
                    p.left = convert2Binary(child, !right);
                    p = p.left;
                }
            }
        }
        return n;
    }

    private static MultiNode binary2Multi(Node root) {
//        return convert2Multi(null, root, false);
        return convert2Multi(root, false);
    }

    private static MultiNode convert2Multi(Node node, boolean right) {
        if (node == null) {
            return null;
        }

        Node temp = node;
        MultiNode result = new MultiNode(null, node.key);

        if (right) {
            while ((temp = temp.right) != null) {
                result.addChildren(convert2Multi(temp, !right));
            }
        }
        else {
            while ((temp = temp.left) != null) {
                result.addChildren(convert2Multi(temp, !right));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        MultiNode root = new MultiNode(null, 1);
        MultiNode _2 = new MultiNode(root, 2);
        MultiNode _3 = new MultiNode(root, 3);
        MultiNode _4 = new MultiNode(root, 4);
        MultiNode _5 = new MultiNode(_3, 5);
        MultiNode _6 = new MultiNode(_3, 6);

        Node result = multi2Binary(root);
        result.print();

        MultiNode r = binary2Multi(result);
        r.print();

    }

    // Second way (my own implementation)
    private static MultiNode convert2Multi(MultiNode root, Node node, boolean right) {
        if (node == null) {
            return null;
        }
        MultiNode n = new MultiNode(null, node.key);
        if (root == null) {
            root = n;
        }
        if (right && node.right != null) {
            root.addChildren(convert2Multi(root, node.right, right));
        }
        else if (!right && node.left != null) {
            root.addChildren(convert2Multi(root, node.left, right));
        }

        // there is the second child -> new branch
        if (right && node.left != null) {
            n.addChildren(convert2Multi(n, node.left, !right));
        }
        else if (!right && node.right != null) {
            n.addChildren(convert2Multi(n, node.right, !right));
        }
        return n;
    }
}

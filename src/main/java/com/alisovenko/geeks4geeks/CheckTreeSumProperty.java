package com.alisovenko.geeks4geeks;

import com.alisovenko.base.BinaryNode;
import com.alisovenko.base.Node;

/**
 * @author alisovenko 10.01.15
 */
public class CheckTreeSumProperty {
    public static int correct(BinaryNode node) {
        if (node == null)
            return 0;
        int left = correct(node.left);
        int right = correct(node.right);

        // If either left or right is incorrect - just propagating it topwards
        if (left < 0 || right < 0) {
            return -1;
        }
        if (left == 0 && right == 0) {
            // no check - we are at the bottom node
            return node.key;
        }
        if (left + right != node.key) {
            return -1;
        }
        return node.key;
    }

    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(10);
        root.addToLeft(8).addToLeft(3);
        root.left.addToRight(5);
        root.addToRight(2).addToLeft(2);

        System.out.println(correct(root));
    }
}

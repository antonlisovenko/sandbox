package com.alisovenko.coderust.tree;

import com.alisovenko.base.Node;

/**
 * Delete all subtrees that sum to 0.
 *
 *   6
 * |   \
 * 3      1
 * | \   | \
 * -2 -1 -1 4
 *          |
 *          -4
 * @author alisovenko 28.10.14
 */
public class DeleteAllZeroSums {
    public static int findAndDeleteZeros(Node node) {
        if (node == null) {
            return 0;
        }

        int leftZ = findAndDeleteZeros(node.left);
        int rightZ = findAndDeleteZeros(node.right);

        if (leftZ == 0) {
            node.left = null;
        }
        if (rightZ == 0) {
            node.right = null;
        }
        return leftZ + rightZ + node.key;
    }

    public static void main(String[] args) {
        Node n1 = new Node(null, 6);
        Node n2 = new Node(n1, 3, true);
        Node n3 = new Node(n2, -2, true);
        Node n4 = new Node(n2, -1, false);
        Node n5 = new Node(n1, 1, false);
        Node n6 = new Node(n5, -1, true);
        Node n7 = new Node(n5, 4, false);
        Node n8 = new Node(n7, -4, false);

        findAndDeleteZeros(n1);
        n1.print();
    }
}

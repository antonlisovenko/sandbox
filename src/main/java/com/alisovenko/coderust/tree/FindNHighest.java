package com.alisovenko.coderust.tree;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 *   |  \
 *   6    12
 * @author alisovenko 28.10.14
 */
public class FindNHighest {
    public static Node findHighest(Node node, int currentRank, int k) {
        if (node == null) {
            return null;
        }

        Node result;
        // is the searhed node in the left subtree?
        if (currentRank + getRank(node.left) >= k) {
            result = findHighest(node.left, currentRank, k);
        }
        // current node?
        else if (currentRank + getRank(node.left) + 1 == k) {
            return node;
        }
        // right subtree?
        else {
            result = findHighest(node.right, currentRank + getRank(node.left) + 1, k);
        }

        if (result == null) {
            return node;
        }
        return result;
    }

    private static int getRank(Node p) {
        if (p == null) {
            return 0;
        }
        return p.rank;
    }

    public static void main(String[] args) {
        Node root = new Node(null, 5);
        root.add(3);
        root.add(1);
        root.add(8);
        root.add(7);
        root.add(9);
        root.add(12);
        root.add(6);

        System.out.println(findHighest(root, 0, 4));
        System.out.println(findHighest(root, 0, 2));
        System.out.println(findHighest(root, 0, 7));
        System.out.println(findHighest(root, 0, 14));
    }
}

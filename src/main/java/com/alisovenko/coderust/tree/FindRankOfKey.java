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
public class FindRankOfKey {
    public static int rank(Node node, int key) {
        if (node == null) {
            return 0;
        }
        if (node.key == key) {
            return (node.left != null ? node.left.rank : 0) + 1;
        }
        if (node.key > key) {
            return rank(node.left, key);
        } else {
            return (node.left != null ? node.left.rank : 0) + 1 + rank(node.right, key);
        }
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
        System.out.println(rank(root, 6));
        System.out.println(rank(root, 15));
        System.out.println(rank(root, 3));

    }

}

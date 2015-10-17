package com.alisovenko.geeks4geeks;

import com.alisovenko.base.Node;

/**
 *          7
 *        /  \
 *       4    11
 * |    / \   / \
 *     1   6 9  14
 *      \         \
 *      3          15
 *     /
 *    2
 * @author alisovenko 15.01.15
 */
public class BinaryTreeRemoveLowLevel {
    public static void removeExcess(Node root, int l) {
        dfsAndRemove(root, 1, l);
    }
    private static boolean dfsAndRemove(Node n, int level, int M) {
        if (n == null) {
            // if level is bigger than M that means the parent level is >= M that is acceptable - we need to leave the node and root
            return  level > M;
        }
        if (level >= M) {
            // true means we should leave the node and all the path to root, no futher traversing
            return true;
        }

        boolean l = dfsAndRemove(n.left, level + 1, M);
        boolean r = dfsAndRemove(n.right, level + 1, M);

        if (!l) {
            // left subtree does not meet M - remove it
            System.out.println("Removing " + n.left);
            n.left = null;
        }
        if (!r) {
            System.out.println("Removing " + n.right);
            n.right = null;
        }
        if (l || r) {
            // return true as the indication that some of childs is >= M
            return true;
        }
        // we found the node that does not have descendants with level >= M - can remove ourselves
        return false;
    }

    public static void main(String[] args) {
        Node root = new Node(7);
        root.add(4);
        root.add(11);
        root.add(6);
        root.add(1);
        root.add(9);
        root.add(14);
        root.add(15);
        root.add(3);
        root.add(2);


        root.print();

        removeExcess(root, 5);

        root.print();
    }
}

package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 10
 *     | \
 *     9  12
 *
 * Binary search to the node (saving successor as we move to the left) and then check right child - if it is there - get
 * the leftmost descendant. Otherwise return the gathered successor
 *
 * @author alisovenko 13.11.14
 */
public class InOrderSuccessorNoParent {
    public static Node successor(Node root, int key) {
        Node successor = null;
        while (root != null) {
            if (root.key < key) {
                root = root.right;
            }
            else if (root.key > key) {
                successor = root;
                root = root.left;
            }
            else {
                if (root.right != null) {
                    Node p = root.right;

                    while (p.left != null) {
                        p = p.left;
                    }
                    successor = p;
                }
                root = null;
            }
        }
        return successor;
    }

    public static void main(String[] args) {
        Node root = new Node(null, 5);
        root.add(3);
        root.add(1);
        root.add(8);
        root.add(7);
        root.add(10);
        root.add(9);
        root.add(12);
        root.print();

        assert successor(root, 3).key == 5;
        assert successor(root, 1).key == 3;
        assert successor(root, 5).key == 7;
        assert successor(root, 8).key == 9;
        assert successor(root, 9).key == 10;
        assert successor(root, 12) == null;
    }
}

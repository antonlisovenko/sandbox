package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * <p>
 *  7
 * |  \
 * 2   12
 * | \ |
 * 1 4 8
 * |    \
 * 3     11
 *       |
 *      10
 *
 * @author alisovenko 25.10.14
 */
public class PostOrderIteratorParent {
    private Node current;

    public PostOrderIteratorParent(Node root) {
        // Move to the leftmost node
        current = leftmost(root);
    }

    public Node next() {
        Node next = current;
        if (next == null) {
            return null;
        }

        // we came to root, no further iteration possible
        if (current.parent == null) {
            current = null;
            return next;
        }

        Node cur = current;
        // If we are left child of our parent - move to the leftmost child of right sibling
        if (cur == cur.parent.left) {
            if (cur.parent.right != null) {
                // search the left (right) most node in the right sibling
                cur = leftmostOrRightmost(cur.parent.right);

                current = cur;
                return next;
            } else {
                // If no right sibling - return the parent
                current = cur.parent;
                return next;
            }
        } else {
            // If we are right child of parent - just return it (left subtree is already traversed)
            current = cur.parent;
            return next;
        }
    }


    private Node leftmost(Node n) {
        Node current = n;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node leftmostOrRightmost(Node n) {
        Node current = n;
        while (current.left != null || current.right != null) {
            if (current.left != null) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return current;
    }


    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        PostOrderIteratorParent it = new PostOrderIteratorParent(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }

        n = new Node(null, 7);
        n.add(2);
        n.add(1);
        n.add(4);
        n.add(3);
        n.add(12);
        n.add(8);
        n.add(11);
        n.add(10);
        n.print();

        it = new PostOrderIteratorParent(n);
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

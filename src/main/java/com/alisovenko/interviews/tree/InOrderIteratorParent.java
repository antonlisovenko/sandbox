package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * @author alisovenko 25.10.14
 */
public class InOrderIteratorParent {
    private Node current;

    public InOrderIteratorParent(Node root) {
        // Move to the leftmost node
        current = leftmost(root);
    }

    public Node next() {
        // return always current, if not null
        Node c = current;
        if (c == null) {
            return null;
        }
        // if right is not null - return the leftmost
        if (current.right != null) {
            current = leftmost(current.right);
            return c;
        }
        Node parent = current.parent;
        Node n = current;

        // iterate through parents until we find the parent, if the node is left child of it
        while (parent != null && parent.left != n) {
            n = parent;
            parent = parent.parent;
        }
        current = parent;
        return c;
    }

    private Node leftmost(Node n) {
        Node current = n;
        while (current.left != null) {
            current = current.left;
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

        InOrderIteratorParent it = new InOrderIteratorParent(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

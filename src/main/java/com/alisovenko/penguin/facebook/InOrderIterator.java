package com.alisovenko.penguin.facebook;

import com.alisovenko.base.Node;

import java.util.Iterator;

/**
 * @author alisovenko 18.10.14
 */
public class InOrderIterator implements Iterator<Node>{
    Node next;

    public InOrderIterator(Node next) {
        this.next = next;
    }


    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Node next() {
        Node result = next;

        fetchNext();

        return result;
    }

    private void fetchNext() {
        if (next.right != null) {
            next = findTheMostLeftChild(next.right);
        }
        else {
            next = findTheFirstRightAncestor(next);
        }
    }

    private Node findTheMostLeftChild(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    private Node findTheFirstRightAncestor(Node n) {
        Node c = n;
        Node p = c.parent;

        while (p != null && p.left != c) {
            c = p;
            p = p.parent;
        }
        return p == null ? n : p;
    }
}

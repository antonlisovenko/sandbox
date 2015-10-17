package com.alisovenko.base;

/**
 * @author alisovenko 26.09.14
 */
public class Node {
    public Node left;
    public Node right;
    public Node parent;
    public final int key;
    boolean marked;
    public int rank = 1;
    public Node next;

    public Node(int key) {
        this.parent = null;
        this.key = key;
    }
    public Node(Node parent, int key) {
        this.parent = parent;
        this.key = key;
    }

    public Node(Node parent, int key, boolean left) {
        this.parent = parent;
        this.key = key;

        if (left) {
            parent.left = this;
        }
        else {
            parent.right = this;
        }
    }

    public void add(int value) {
        add(this, value);
    }

    public static Node add(Node n, int value) {
        if (n == null) {
            return new Node(null, value);
        }
        if (value <= n.key) {
            n.left = add(n.left, value);
            n.left.parent = n;
        }
        else {
            n.right = add(n.right, value);
            n.right.parent = n;
        }
        n.rank = coalesce(n.left, 0) + coalesce(n.right, 0) + 1;
        return n;

    }

    private static int coalesce(Node p, int i) {
        if (p == null) {
            return i;
        }
        return p.rank;
    }

    public Node addNoBS(int value) {
        if (this.left == null) {
            this.left = new Node(this, value);
            return this.left;
        }
        if (this.right == null) {
            this.right = new Node(this, value);
            return this.right;
        }
        throw new IllegalStateException();
    }

    public void mark() {
        marked = true;
    }

    private void print(Node node) {
        if (node != null) {
            print(node.left);
            System.out.println(node.key);
            print(node.right);
        }
    }
    public void print() {
        System.out.println("--");
        print(this);
        System.out.println("--");
    }

    public void printUpword() {
        printUp(this);
    }

    private void printUp(Node node) {
        if (node == null) {
            return;
        }
        printUp(node.parent);
        System.out.println(node);
    }

    @Override
    public String toString() {
        return String.valueOf(key);
                //", parent=" + (parent == null ? "empty" : parent.key) +
                //'}';
    }
}

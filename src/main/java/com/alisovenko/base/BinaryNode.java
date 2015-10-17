package com.alisovenko.base;

/**
 * @author alisovenko 10.01.15
 */
public class BinaryNode {
    public BinaryNode left;
    public BinaryNode right;
    public final int key;

    public BinaryNode(int key) {
        this.key = key;
    }

    public BinaryNode addToLeft(BinaryNode n) {
        this.left = n;
        return n;
    }
    public BinaryNode addToRight(BinaryNode n) {
        this.right = n;
        return n;
    }
    public BinaryNode addToLeft(int n) {
        this.left = new BinaryNode(n);
        return left;
    }
    public BinaryNode addToRight(int n) {
        this.right = new BinaryNode(n);
        return right;
    }
}

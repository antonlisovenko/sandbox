package com.alisovenko.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 29.11.14
 */
public class MultiNode {
    public List<MultiNode> children = new ArrayList<>();
    public MultiNode parent;
    public int key;

    public MultiNode(MultiNode parent, int value, MultiNode... children) {
        this.parent = parent;
        this.key = value;
        this.children.addAll(Arrays.asList(children));
        if (parent != null) parent.addChildren(this);
    }

    public void addChildren(MultiNode... children) {
        this.children.addAll(Arrays.asList(children));
    }

    public void print() {
        print(this);
    }

    private static void print(MultiNode node) {
        System.out.println(node.key);
        for (final MultiNode child : node.children) {
            print(child);
        }
    }

    @Override
    public String toString() {
        return "MultiNode{" +
                "key=" + key +
                '}';
    }
}

package com.alisovenko.cracking.the.interview._4_trees_graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author alisovenko 29.08.14
 */
public class _4_4 {
    public static void createLinked(Node current, int depth, List<LinkedList<Node>> linkedLists) {
        if (current == null) {
            return;
        }
        createLinked(current.left, depth + 1, linkedLists);
        if (linkedLists.size() <= depth) {
            linkedLists.addAll(Collections.nCopies(depth + 1, new LinkedList<>()));
        }
        linkedLists.get(depth).add(current);

        createLinked(current.right, depth + 1, linkedLists);
    }

    public static void main(String[] args) {
        Node root = new Node("1");
        root.left = new Node("2.1");
        root.right = new Node("2.2");
        root.left.left = new Node("3.1");
        root.left.right = new Node("3.2");
        root.right.left = new Node("3.3");
        root.right.right = new Node("3.4");

        ArrayList<LinkedList<Node>> linkedLists = new ArrayList<>(0);
        createLinked(root, 0, linkedLists);

        System.out.println(linkedLists);
    }
}

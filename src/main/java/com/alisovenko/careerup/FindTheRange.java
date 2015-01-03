package com.alisovenko.careerup;

import com.alisovenko.base.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the range between ordered keys.
 *
 * @author alisovenko 05.10.14
 */
public class FindTheRange {
    public static void findTheRange(Node n, int key1, int key2, List<Node> found) {
        if (n == null || n.key < key1 || n.key > key2) {
            return;
        }
        findTheRange(n.left, key1, key2, found);
        found.add(n);
        findTheRange(n.right, key1, key2, found);
    }

    public static void main(String[] args) {
        Node root = new Node(null, 8);
        root.add(4);
        root.add(2);
        root.add(6);
        root.add(7);
        root.add(15);
        root.add(17);
        root.add(19);
        root.add(11);

        List<Node> found = new ArrayList<>();
        findTheRange(root, 8, 13, found);

        System.out.println(found);
    }
}

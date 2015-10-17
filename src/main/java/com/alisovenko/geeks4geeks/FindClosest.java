package com.alisovenko.geeks4geeks;

import com.alisovenko.base.Node;

/**
 * @author alisovenko 11.07.15
 */
public class FindClosest {
    public static Node findClosest(Node node, int k, Node closest) {
        if (node == null) {
            return closest;
        }
        if (node.key == k) {
            return node;
        }
        if (closest == null || Math.abs(closest.key - k) > node.key - k) {
            closest = node;
        }

        if (node.key < k) {
            return findClosest(node.right, k, closest);
        }
        return findClosest(node.left, k, closest);
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();


        System.out.println(findClosest(n, 10, null));
        System.out.println(findClosest(n, 2, null));
        System.out.println(findClosest(n, 7, null));
        System.out.println(findClosest(n, 4, null));
    }
}

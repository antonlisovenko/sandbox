package com.alisovenko.geeks4geeks;

import com.alisovenko.base.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * @author alisovenko 09.01.15
 */
public class ConnectTreeNodesHorizontally {

    public static void linkNodes(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Queue<Node> t = new LinkedList<>();

        queue.add(node);

        Node prev = null;
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            if (prev == null) {
                prev = n;
            }
            else {
                prev.next = n;
                prev = n;
            }
            if (n.left != null)
                t.add(n.left);
            if (n.right != null)
                t.add(n.right);

            if (queue.isEmpty()) {
                queue = t;
                t = new LinkedList<>();
                prev = null;
            }
        }
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        linkNodes(n);
        System.out.println();
    }
}

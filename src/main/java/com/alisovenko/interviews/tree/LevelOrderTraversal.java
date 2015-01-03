package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 10
 *     | \
 *     9  12
 * @author alisovenko 13.11.14
 */
public class LevelOrderTraversal {
    private static final Node NULL = new Node(null, -1);

    public static void traverse(Node root) {
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(root);
        queue.add(NULL);

        int numberOfNodesOnLevel = 1;
        while (!queue.isEmpty()) {
            Node n = queue.poll();

            if (n != NULL) {
                System.out.print(n + ", ");
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
                numberOfNodesOnLevel++;
            } else if (n == NULL && numberOfNodesOnLevel > 0){
                System.out.println();
                queue.add(NULL);
                numberOfNodesOnLevel = 0;
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(null, 5);
        root.add(3);
        root.add(1);
        root.add(8);
        root.add(7);
        root.add(10);
        root.add(9);
        root.add(12);
        root.print();

        traverse(root);
    }
}

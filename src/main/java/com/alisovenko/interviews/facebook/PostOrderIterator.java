package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 *  5
 * |  \
 * 4    8
 * |   |  \
 * 1   7   11
 *  \      |
 *  3      9
 *  |       \
 *  2        10
 * @author alisovenko 18.01.15
 */
public class PostOrderIterator {
    Stack<Node> stack = new Stack<>();

    public PostOrderIterator(Node root) {
        // adding the left branch, if no left left - adding right nodes
        while (root != null) {
            stack.push(root);
            root = root.left != null ? root.left : root.right;
        }
    }

    public Node next() {
        if (stack.isEmpty()) {
            return null;
        }

        Node next = stack.pop();

        // Checking the leftmost-rightmost child of parent's right child
        if (!stack.isEmpty()) {
            Node parent = stack.peek();

            if (parent.left == next) {
                Node t = parent.right;

                while (t != null) {
                    stack.push(t);
                    t = t.left != null ? t.left : t.right;
                }
            }
        }

        return next;
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(4);
        n.add(1);
        n.add(3);
        n.add(2);
        n.add(8);
        n.add(7);
        n.add(11);
        n.add(9);
        n.add(10);
        n.print();

        PostOrderIterator it = new PostOrderIterator(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

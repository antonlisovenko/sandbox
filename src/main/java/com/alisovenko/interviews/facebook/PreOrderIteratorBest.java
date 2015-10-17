package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 * 5
 * |  \
 * 4    8
 * |   |  \
 * 1   7   11
 * \      |
 * 3      9
 * |       \
 * 2        10
 *
 * @author alisovenko 08.12.14
 */
public class PreOrderIteratorBest {
    private final Stack<Node> stack = new Stack<>();

    public PreOrderIteratorBest(Node root) {
        stack.push(root);
    }

    public Node next() {
        if (stack.isEmpty()) {
            return null;
        }

        Node result = stack.peek();

        // if the current node has only one child or no children at all - we will not use it futher
        if (result.left == null || result.right == null) {
            stack.pop();
        }

        // for any node first try the left or right child if null
        if (result.left != null || result.right != null) {
            stack.push(result.left != null ? result.left : result.right);
        }
        // we are at leaf - let's take the next unprocessed parent
        else {
            // check no parents left
            if (stack.isEmpty()) return result;

            // then take next parent and put its right child to stack
            Node parent = stack.pop();

            if (parent != null) {
                stack.push(parent.right);
            }
        }

        return result;
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

        PreOrderIteratorBest it = new PreOrderIteratorBest(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

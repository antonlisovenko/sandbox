package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 *
 * @author alisovenko 04.11.14
 */
public class InOrderNonRecursive {
    public static void traverse(Node root) {
        Node current = root;
        Stack<Node> stack = new Stack<>();

        while (!stack.empty() || current != null) {
            Node c = current;

            while (c != null) {
                stack.push(c);
                c = c.left;
            }

            c = stack.pop();
            System.out.println(c);

            current = c.right;
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

        InOrderNonRecursive.traverse(n);
    }
}

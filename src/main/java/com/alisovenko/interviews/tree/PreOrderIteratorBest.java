package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
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

        if (result.left != null) {
            stack.push(result.left);
        }else if (result.right != null) {
            stack.push(result.right);
        }
        else {
            stack.pop();
            Node parent, node = result;

            while ((parent = peek(stack)) != null && (parent.right == node || parent.right == null)) {
                stack.pop();
                node = parent;
            }
            if (parent != null) {
                stack.push(parent.right);
            }
        }

        return result;
    }

    private Node peek(Stack<Node> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        PreOrderIteratorBest it = new PreOrderIteratorBest(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

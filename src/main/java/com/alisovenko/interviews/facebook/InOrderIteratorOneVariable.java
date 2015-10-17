package com.alisovenko.interviews.facebook;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * This is optimized (O(lnN) for memory) version of inOrder iteration that does not track visited nodes
 * Idea:
 * - keep the current node as the field
 * - loop until current node has left fields, push them to stack
 * - otherwise pop the stack - current parent will be taken from the stack and push its right child to the stack
 *
 * This way we get rid of the parent after its left subtree is processed
 *
 * @author alisovenko 25.10.14
 */
public class InOrderIteratorOneVariable {
    private final Stack<Node> stack = new Stack<>();
    private Node current;

    public InOrderIteratorOneVariable(Node root) {
        current = root;
    }

    public Node next() {
        Node result;
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
                continue;
            }
            result = stack.pop();
            current = result.right;
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        InOrderIteratorOneVariable it = new InOrderIteratorOneVariable(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

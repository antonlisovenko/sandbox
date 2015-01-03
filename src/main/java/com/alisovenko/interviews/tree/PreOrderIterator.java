package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * <p>
 * Logics:
 * - in a loop:
 * - peek the stack for head (n)
 * - if the n contains left node and it was not visited - push it to stack
 * - else if the n contains the right node and it was not visited - push it to stack
 * - if this is the very first time we came to current node (e.g. this is not backtracking) - we go out the loop and return the current node
 * - if no children and we are in backtracking (e.g. return in recurse) - simply pop() the stack and loop further
 * - mark current element visited and return it. It is left in stack for further invocations until it is poped.
 * <p>
 * Main differences from post-/in-: return the top of stack (peek, not pop), fill the stack with left
 * (or right, if no left) child for future calls. Backtrack and pop element, if no unvisited children
 *
 * @author alisovenko 25.10.14
 */
public class PreOrderIterator {
    private final Stack<Node> stack = new Stack<>();
    private final Set<Node> visited = new HashSet<>();

    public PreOrderIterator(Node root) {
        stack.push(root);
    }

    public Node next() {
        Node result = null;
        boolean done = false;

        while (!done && !stack.isEmpty()) {
            Node next = stack.peek();

            if (ok(next.left) || ok(next.right) || !visited.contains(next)) {
                if (ok(next.left)) {
                    stack.push(next.left);
                } else if (ok(next.right)) {
                    stack.push(next.right);
                }
                if (!visited.contains(next)) {
                    done = true;
                    result = next;
                }
            } else {
                stack.pop();
            }
        }
        visited.add(result);
        return result;

    }

    private boolean ok(Node n) {
        return (n != null && !visited.contains(n));
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        PreOrderIterator it = new PreOrderIterator(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

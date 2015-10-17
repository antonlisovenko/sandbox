package com.alisovenko.interviews.facebook;

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
 * Idea:
 * - loop through left nodes, push them to stack
 * - if no left nodes left for current node and current was not visited (e.g. no backtracking) - peek (if right child
 * not visited) or push the element (if both children were visited) from stack
 * - if the current node was visited (e.g. returned on iteration cycle) - move to the right and put elements to
 * stack until the element with no left children is met - return it
 *
 * @author alisovenko 25.10.14
 */
public class InOrderIteratorSetOfVisited {
    private final Stack<Node> stack = new Stack<>();
    private final Set<Node> visited = new HashSet<>();

    public InOrderIteratorSetOfVisited(Node root) {
        stack.push(root);
    }

    public Node next() {
        while (!stack.isEmpty()) {
            Node next = stack.peek();

            if (ok(next.left)) {
                stack.push(next.left);
                // we continue the loop if there is unvisited left child
                continue;
            }

            if (!visited.contains(next)) {
                // we just came to the leaf left/leaf right or middle node and it was not visited
                visited.add(next);
                // peek if right node is unprocessed (popping will result in skipping the right subtree iteration)
                return ok(next.right) ? stack.peek() : stack.pop();
            }

            // if we are here - then the middle element was already processed and we wend down to the right subtree
            if (ok(next.right)) {
                stack.push(next.right);
            }
        }
        return null;
    }

    private boolean ok(Node n) {
        return n != null && !visited.contains(n);
    }


    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        InOrderIteratorSetOfVisited it = new InOrderIteratorSetOfVisited(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

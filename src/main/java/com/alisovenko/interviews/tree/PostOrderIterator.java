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
 * On each stage we try go get to the deepest not visited node - left or right (if no left)
 * - peek the head of stack (current)
 * - check if n has not visited children - if has - add them to stack and mark visited
 * - change current node to child
 * - end loop if no more unvisited children
 * - return the head of stack (either one of end leafs or their parent, or its parent..)
 * <p>
 * Main differences from pre-/in-: loop down and fill the stack until meet the terminal node - pop the top of stack.
 *
 * @author alisovenko 23.10.14
 */
public class PostOrderIterator {
    private Stack<Node> stack = new Stack<>();
    private Set<Node> visited = new HashSet<>();

    public PostOrderIterator(Node root) {
        stack.push(root);
    }

    public Node next() {
        if (stack.isEmpty()) {
            return null;
        }

        // try the next stack element
        Node current = stack.peek();

        // if it has not visited children
        while (check(current.left) || check(current.right)) {
            if (check(current.left)) {
                // push and visit the left if there is free
                current = push(current.left);
            } else {
                // push and visit the right if there is free
                current = push(current.right);
            }
        }

        // return the last stack node (either the "parent" or some end leafs)
        return stack.pop();
    }

    private Node push(Node node) {
        stack.push(node);
        visited.add(node);
        return node;
    }

    private boolean check(Node node) {
        return node != null && !visited.contains(node);
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        PostOrderIterator it = new PostOrderIterator(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}
package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

import java.util.Stack;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * This is the BEST version of iterator - without any additional variables!
 * <p>
 * The main invariant - on each next() call the top of stack must contain the next element. So, the next()
 * makes stack.pop() call and then prepares the stack so that it contained valid chain of elements up to the next one
 *
 * Steps:
 * - initialize the stack in constructor (to the leftmost element)
 * - next(): pop() the stack - this is the result
 * - next(): prepare the next element - this is the leftmost element of the right child. Push all interim nodes to stack
 *
 * @author alisovenko 25.10.14
 */
public class InOrderIteratorBest {
    private final Stack<Node> stack = new Stack<>();

    public InOrderIteratorBest(Node root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public Node next() {
        Node result;
        while (!stack.isEmpty()) {
            result = stack.pop();
            Node temp = result.right;

            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
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

        InOrderIteratorBest it = new InOrderIteratorBest(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}

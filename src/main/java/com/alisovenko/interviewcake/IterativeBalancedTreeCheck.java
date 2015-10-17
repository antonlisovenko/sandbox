package com.alisovenko.interviewcake;

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
 * @author alisovenko 10.03.15
 */
public class IterativeBalancedTreeCheck {
    public static boolean isTreeBalanced(Node root) {
        Stack<Node> stack = new Stack<>();

        Set<Node> visited = new HashSet<>();
        stack.push(root);
        visited.add(root);

        int minDepth = Integer.MAX_VALUE;
        int maxDepth = 0;

        while (!stack.isEmpty()) {
            Node n = stack.peek();
            if (n.left == null && n.right == null) {
                // we are at leaf
                minDepth = Math.min(minDepth, stack.size());
                maxDepth = Math.max(maxDepth, stack.size());
                stack.pop();
            }
            else if(n.left != null && !visited.contains(n.left)) {
                stack.push(n.left);
                visited.add(n.left);
            }
            else if(n.right != null && !visited.contains(n.right)) {
                stack.push(n.right);
                visited.add(n.right);
            }
            else {
                stack.pop();
            }
        }
        return maxDepth - minDepth < 2;
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        System.out.println(isTreeBalanced(n));

        n.add(0);
        n.add(-1);
        System.out.println(isTreeBalanced(n));

    }


}

package com.alisovenko.cracking.the.interview._4_trees_graphs;

/**
 * @author alisovenko 24.08.14
 */
public class _4_1 {
    private static int currDepth = -1;
    private static int currDepth2 = -1;

    public static void isBalanced(Node tree) {
        walk(tree, 0);
    }

    private static boolean walk(Node node, int depth) {
        if (node == null) {
            // return true to indicate that we just passed the leaf
            return true;
        }
        int newDepth = depth + 1;
        if (walk(node.left, newDepth)) {
            checkBalance(newDepth);
        }
        if (walk(node.right, newDepth)) {
            checkBalance(newDepth);
        }
        return false;
    }

    private static void checkBalance(int i) {
        // This is just the first depth collected
        if (currDepth == -1) {
            currDepth = i;
            return;
        }

        if (currDepth2 == -1) {
            if (Math.abs(i - currDepth) > 1) {
                assert false : "Tree unbalanced!";
            }
            currDepth2 = i;
        }

        if (i != currDepth && i != currDepth2) {
            assert false : "Tree unbalanced!";
        }

    }
}

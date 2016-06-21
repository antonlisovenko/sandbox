package com.alisovenko.leetcode;

import com.alisovenko.base.Utils;
import com.alisovenko.base.Utils.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author alisovenko
 *         2/17/16.
 */
public class ClosestBSTreeValue2 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new LinkedList<Integer>();
        // populate the predecessor and successor stacks
        Stack<TreeNode> pred = new Stack<TreeNode>();
        Stack<TreeNode> succ = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null) {
            if (target <= curr.val) {
                succ.push(curr);
                curr = curr.left;
            } else {
                pred.push(curr);
                curr = curr.right;
            }
        }
        while (k > 0) {
            if (pred.empty() && succ.empty()) {
                break;
            } else if (pred.empty()) {
                result.add(getSuccessor(succ));
            } else if (succ.empty()) {
                result.add(getPredecessor(pred));
            } else if (Math.abs(target - pred.peek().val) < Math.abs(target - succ.peek().val)) {
                result.add(getPredecessor(pred));
            } else {
                result.add(getSuccessor(succ));
            }
            k--;
        }
        return result;
    }

    private int getPredecessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.left;
        while (p != null) {
            st.push(p);
            p = p.right;
        }
        return popped.val;
    }

    private int getSuccessor(Stack<TreeNode> st) {
        TreeNode popped = st.pop();
        TreeNode p = popped.right;
        while (p != null) {
            st.push(p);
            p = p.left;
        }
        return popped.val;
    }


    public static void main(String[] args) {
        TreeNode root = Utils.build(new Integer[]{6, 4, 9, 1, 5, 8, null}, 0);
        System.out.println(new ClosestBSTreeValue2().closestKValues(root, 6.0, 1));
    }

}

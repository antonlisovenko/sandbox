package com.alisovenko.leetcode;

import com.alisovenko.base.Utils;
import com.alisovenko.base.Utils.TreeNode;

import java.util.*;

/**
 * @author alisovenko
 *         2/16/16.
 */
public class ClosestBSTreeValue {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if (root == null) throw new RuntimeException();

        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> ancPath = new LinkedList<>();
        LinkedList<TreeNode> prePath = new LinkedList<>();

        TreeNode n = findClosest(root, target, root);

        scanFromClosest(root, n, target, ancPath, prePath, k, res);

        return res;
    }

    private void scanFromClosest(TreeNode n, TreeNode closest, double target, LinkedList<TreeNode> ancPath, LinkedList<TreeNode> prePath, int k, List<Integer> res) {
        if (n == null) return;

        if (n == closest) {
            res.add(n.val);

            TreeNode pre = predecessor(n, prePath);
            TreeNode anc = ancestor(n, ancPath);

            while (res.size() < k) {
                if (pre == null && anc == null) throw new RuntimeException();
                if (pre == null || (anc != null && Math.abs(anc.val - target) <= Math.abs(pre.val - target))) {
                    res.add(anc.val);
                    anc = ancestor(anc, ancPath);
                } else if (anc == null || (pre != null && Math.abs(pre.val - target) < Math.abs(anc.val - target))) {
                    res.add(pre.val);
                    pre = predecessor(pre, prePath);
                }
            }
        } else {
            ancPath.push(n);
            prePath.push(n);

            if (closest.val < n.val) scanFromClosest(n.left, closest, target, ancPath, prePath, k, res);
            else scanFromClosest(n.right, closest, target, ancPath, prePath, k, res);
        }

    }

    TreeNode findClosest(TreeNode n, double target, TreeNode closest) {
        // Normal lookup for closest element
        if (n == null) return closest;
        if (n.val == target) return n;

        if (n.val == 5) {
            System.out.println();
        }

        if (Math.abs(target - n.val) < Math.abs(target - closest.val)) closest = n;

        if (target < n.val) return findClosest(n.left, target, closest);
        else return findClosest(n.right, target, closest);
    }

    private TreeNode predecessor(TreeNode n, LinkedList<TreeNode> prePath) {
        TreeNode next = n.left;

        // Checking left subtree
        if (next != null) {
            prePath.push(next);
            while (next.right != null) {
                next = next.right;
                prePath.push(next);
            }
            prePath.poll();
            return next;
        }

        // Looking for the first parent "from the left"
        TreeNode current = n;
        next = prePath.poll();

        while (next != null && next.left == current) {
            current = next;
            next = prePath.poll();
        }
        return next;
    }

    private TreeNode ancestor(TreeNode n, LinkedList<TreeNode> ancPath) {
        TreeNode next = n.right;

        // Checking right subtree
        if (next != null) {
            ancPath.push(next);
            while (next.left != null) {
                next = next.left;
                ancPath.push(next);
            }
            ancPath.poll();
            return next;
        }

        // Looking for the first parent "from the right"
        TreeNode current = n;
        next = ancPath.poll();

        while (next != null && next.right == current) {
            current = next;
            next = ancPath.poll();
        }
        return next;
    }

    public static void main(String[] args) {
        TreeNode root = Utils.build(new Integer[]{41,37,44,24,39,42,48,1,35,38,40,null,43,46,49,0,2,30,36,null,null,null,null,null,null,45,47,null,null,null,null,null,4,29,32,null,null,null,null,null,null,3,9,26,null,31,34,null,null,7,11,25,27,null,null,33,null,6,8,10,16,null,null,null,28,null,null,5,null,null,null,null,null,15,19,null,null,null,null,12,null,18,20,null,13,17,null,null,22,null,14,null,null,21,23}, 0);
        System.out.println(new ClosestBSTreeValue().closestKValues(root, 5.1, 45));
    }

}

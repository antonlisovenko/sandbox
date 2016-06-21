package com.alisovenko.leetcode;

import com.alisovenko.base.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author alisovenko
 *         2/19/16.
 */
public class DeseriallizeBTLevelOrder {
    public static Utils.TreeNode deserialize(String str) {
        str = str.replace("[", "").replace("]", "");
        List<Utils.TreeNode> nodes = Arrays.stream(str.split(","))
                .map(s -> Objects.equals(s, "null") ? null : new Utils.TreeNode(Integer.parseInt(s)))
                .collect(Collectors.toList());

        int child = 1;

        for (Utils.TreeNode node : nodes) {
            if (nodes.size() <= child) break;
            if (node != null) {
                node.left = nodes.get(child++);
                node.right = nodes.size() <= child ? null : nodes.get(child++);
            }
        }

        return nodes.get(0);
    }

    public static void main(String[] args) {
        Utils.TreeNode root = new DeseriallizeBTLevelOrder().deserialize("[1,null,3,2,4,null,null,null,5]");
        root = new DeseriallizeBTLevelOrder().deserialize("[2,null,3,2,null,1]");
        System.out.println();
    }
}

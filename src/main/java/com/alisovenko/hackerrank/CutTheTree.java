package com.alisovenko.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/cut-the-tree
 *
 * Very poor description, not clear whether tree is DAG and whether there can be only one root.
 *
 * @author alisovenko
 *         11/20/15.
 */
public class CutTheTree {
    private static class Tree {
        private final Map<Integer, Node> nodeCache;

        private final int totalValue;

        private Node root;

        private Tree(int[] values) {
            nodeCache = new HashMap<>();

            int total = 0;
            for (int i = 1; i < values.length + 1; i++) {
                nodeCache.put(i, new Node(values[i - 1]));
                total += values[i - 1];
            }
            totalValue = total;
        }

        public void addEdge(Node from, Node to) {
            if (root == null) {
                root = from;
            }
            from.add(to);
        }
    }

    private static class Node {
        final int value;
        private int cummulativeValue;

        private List<Node> children = new ArrayList<>();

        private Node(int value) {
            this.value = value;
        }

        public void add(Node node) {
            children.add(node);
        }

        public void addChildrensValue(int childrenValue) {
            this.cummulativeValue = childrenValue + value ;
        }

        public int getCummulativeValue() {
            return cummulativeValue;
        }
    }

    public static void main(String[] args) {

    }
}

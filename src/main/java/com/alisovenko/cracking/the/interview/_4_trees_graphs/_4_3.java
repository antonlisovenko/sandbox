package com.alisovenko.cracking.the.interview._4_trees_graphs;

/**
 * @author alisovenko 24.08.14
 */
public class _4_3 {
    public static Node createTree(int[] sortedArr) {
        return createNode(sortedArr, 0, sortedArr.length - 1);

    }

    private static Node createNode(int[] arr, int from, int to) {
        if (from > to) {
            return null;
        }

        int med = (from + to) / 2;
        int key = arr[(from + to) / 2];
        Node n = new Node(Integer.toString(key));
        n.left = createNode(arr, from, med - 1);
        n.right = createNode(arr, med + 1, to);
        System.out.println("From: " + from + ", to: " + to + " central is " + med);
        return n;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        Node tree = createTree(arr);
        Node.print(tree, 0);

    }
}

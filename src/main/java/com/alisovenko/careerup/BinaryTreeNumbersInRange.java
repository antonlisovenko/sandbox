package com.alisovenko.careerup;

import com.alisovenko.base.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko 03.10.14
 */
public class BinaryTreeNumbersInRange {
    public static void checkAndMark(Node node, int curValue, List<Integer> from, List<Integer> to, int level, boolean markAll) {
        if (markAll
                || from.size() <= level || curValue < from.get(level)
                || to.size() <= level || curValue > to.get(level)) {
            node.mark();
            markAll = true;
        }
        // Special case: if our number is exclusively inside the range - we don't need to recurse any more
        if (node.key > from.get(level) && node.key < to.get(level)) {
            return;
        }
        if (node.left != null) {
            checkAndMark(node.left, curValue * 10 + node.left.key, from, to, level + 1, markAll);
        }
        if (node.right != null) {
            checkAndMark(node.right, curValue * 10 + node.right.key, from, to, level + 1, markAll);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(null, 1);
        Node n1 = root.addNoBS(2);
        Node n3 = n1.addNoBS(3);
        Node n4 = n1.addNoBS(5);

        Node n2 = root.addNoBS(3);
        Node n5 = n2.addNoBS(9);
        Node n6 = n2.addNoBS(6);

        int min = 124;
        int max = 145;
        checkAndMark(root, root.key, split(min), split(max), 0, false);
        System.out.println();
    }

    private static List<Integer> split(int min) {
        int i = min;
        List<Integer> list = new ArrayList<>();
        while (i > 0) {
            list.add(i);
            i /= 10;
        }
        Collections.reverse(list);
        return list;
    }
}

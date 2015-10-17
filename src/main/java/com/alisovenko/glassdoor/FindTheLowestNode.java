package com.alisovenko.glassdoor;

import com.alisovenko.base.Node;

/**
 *  5
 * |  \
 * 4    8
 * |   |  \
 * 1   7   11
 *  \      |
 *  3      9
 *  |       \
 *  2        10
 * @author alisovenko 31.01.15
 */
public class FindTheLowestNode {
    public static Object[] findTheLowestNode(Node n, int level) {
        if (n == null) {
            return null;
        }

        Object[] l = findTheLowestNode(n.left, level + 1);
        Object[] r = findTheLowestNode(n.right, level + 1);
        if (l == null) {
            l = new Object[] {n, level};
        }

        if (r == null) {
            r = new Object[] {n, level};
        }

        int compare= Integer.compare((Integer)l[1], (Integer)r[1]);
        if (compare == 0 || compare < 0) {
            return r;
        }
        if (compare > 0) {
            return l;
        }
        return r;
    }

    public static Node lowest(Node n) {
        return (Node) findTheLowestNode(n, 0)[0];
    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(4);
        n.add(1);
        n.add(3);
        n.add(2);
        n.add(8);
        n.add(7);
        n.add(11);
        n.add(9);
        n.add(10);
        n.add(0);
        n.add(-1);
        n.add(-5);

        System.out.println(lowest(n));
    }
}

package com.alisovenko.careerup;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * http://www.careercup.com/question?id=6335704
 *
 * @author alisovenko 16.10.14
 */
public class KLargestElementInMatrix {
    public static Item find(int[][] matrix, int k) {
        PriorityQueue<Item> queue = new PriorityQueue<>();
        Item i = new Item(matrix, 0, 0);
        queue.add(i);

        int cnt = 0;

        Set<Item> marked = new HashSet<>();
        Item item = i;
        while (cnt != k) {
            item = queue.poll();
            if (item.x < matrix.length - 1) {
                Item down = new Item(matrix, item.x + 1, item.y);
                if (!marked.contains(down)) {
                    queue.add(down);
                    marked.add(down);
                }
            }
            if (item.y < matrix.length - 1) {
                Item right = new Item(matrix, item.x, item.y + 1);
                if (!marked.contains(right)) {
                    queue.add(right);
                    marked.add(right);
                }
            }
            cnt++;
        }
        return item;
    }

    private static class Item implements Comparable<Item> {
        final int x;
        final int y;
        final int value;

        public Item(int[][] matrix, int i, int i1) {
            x = i;
            y = i1;
            value = matrix[x][y];
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(value, o.value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Item item = (Item) o;

            if (x != item.x) return false;
            if (y != item.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {2, 3, 4, 8},
                {3, 4, 6, 9},
                {7, 8, 9, 10},
        };
        System.out.println(find(matrix, 10));
    }
}

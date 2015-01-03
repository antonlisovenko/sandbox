package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * you have a stack of n boxes, with widths w, heights l and depths d. The boxes cannot be rotated and can only be
 * stacked on top of one another if each box in the stack is strictly larger than the box above it in width, height,
 * and depth.
 *
 * Implement a method to build the tallest stack possible, where the height of a stack is the sum of the
 * heights of each box.
 *
 * @author alisovenko 28.12.14
 */
public class MaximumBoxesHeight {
    public static int max(List<Box> head, List<Box> tail, int currentHeight) {
        if (tail.size() == 0) {
            return currentHeight;
        }
        Box lastInHead = head.size() == 0 ? null : head.get(0);
        int max = 0;
        for (int i = 0; i < tail.size(); i++) {
            Box picked = tail.get(i);
            if (lastInHead == null || (lastInHead.compareTo(picked) > 0)) {
                // Add the picked element to head
                head.add(picked);

                // Creating new tail without picked element
                List<Box> newTail;
                if (i > 0) {
                    newTail = new ArrayList<>(tail.subList(0, i));
                } else {
                    newTail = new ArrayList<>();
                }
                newTail.addAll(tail.subList(i + 1, tail.size()));

                max = Math.max(max, max(head, newTail, currentHeight + picked.height));

                // Removing the picked element from head for futher iterations
                head.remove(head.size() - 1);
            }
        }

        return max == 0 ? currentHeight : max;
    }

    private static class Box implements Comparable<Box> {
        int height;
        int width;

        private Box(int height, int width, int depth) {
            this.height = height;
            this.width = width;
            this.depth = depth;
        }

        int depth;

        @Override
        public int compareTo(Box o) {
            return this.depth <= o.depth || this.width <= o.width || this.height <= o.height ? -1 : 1;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "height=" + height +
                    ", width=" + width +
                    ", depth=" + depth +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(max(new ArrayList<>(), Arrays.asList(new Box(3, 4, 1), new Box(8, 6, 2), new Box(7, 8, 3)), 0));

    }
}

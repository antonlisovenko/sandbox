package com.alisovenko.coderust.arrays;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author alisovenko 27.12.14
 */
public class SlidingWindowMax {
    public static void slidingWindowMax(int[] array, int m) {
        TreeSet<Integer> window = new TreeSet<>();

        int start = 0, end = 0, i = 0;

        while (end < array.length) {
            if (end - start >= m) {
                window.remove(array[start]);
                start++;
            }
            int next = array[end];
            Set<Integer> tail = window.headSet(next);
            window.removeAll(tail);
            window.add(next);

            end++;

            System.out.printf("#%d: %d (%d)\n", i++, window.last(), window.size());
        }
    }

    public static void main(String[] args) {
        slidingWindowMax(new int[] {5, 2, 7, 2, 4, 1, 3, 0, 8, 2, 9, 4}, 7);
    }
}

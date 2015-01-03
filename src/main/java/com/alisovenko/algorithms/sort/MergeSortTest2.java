package com.alisovenko.algorithms.sort;

import java.util.Arrays;

/**
 * TBD: add comments for MergeSortTest.java. Created: 11.10.2010
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class MergeSortTest2 extends BaseSortTest {
    /**
     * @see com.alisovenko.algorithms.sort.BaseSortTest#sort()
     */
    @Override
    public void sort(Integer[] array) {
        this.array = mergeSort(array);
    }

    /**
     * Internal method that makes recursive calls.
     * @param a an array of Comparable items.
     */
    private static Integer[] mergeSort(Integer[] a) {
        if (a.length == 0 || a.length == 1) {
            return a;
        }
        int center = a.length / 2;

        Integer[] left = Arrays.copyOfRange(a, 0, center);
        Integer[] right = Arrays.copyOfRange(a, center, a.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     */
    private static Integer[] merge(Integer[] a, Integer[] b) {
        int i = 0, y = 0, t = 0;
        Integer[] result = new Integer[a.length + b.length];

        while (i < a.length || y < b.length) {
            if (i < a.length && y < b.length) {
                if (a[i] < b[y]) {
                    result[t++] = a[i++];
                }
                else {
                    result[t++] = b[y++];
                }
            }
            else if (i == a.length) {
                while (y < b.length) {
                    result[t++] = b[y++];
                }
            }
            else {
                while (i < a.length) {
                    result[t++] = a[i++];
                }
            }
        }

        return result;
    }

}

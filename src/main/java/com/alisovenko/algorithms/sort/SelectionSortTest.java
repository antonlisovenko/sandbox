package com.alisovenko.algorithms.sort;

import java.util.Arrays;

/**
 * TBD: add comments for SelectionAlgorithmTest.java. 
 * 
 * <p>Created: 05.01.2012</p>
 * 
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class SelectionSortTest extends BaseSortTest {
    /**
     * @see com.alisovenko.algorithms.sort.BaseSortTest#sort(java.lang.Integer[])
     */
    @Override
    public void sort(Integer[] array) {
        // Scrolling through the array except for the latest member
        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            int p;
            // 1. Searching for the least element in the non-sorted range
            for (p = (i + 1); p < array.length; p++) {
                if (array[p] < array[minIdx]) {
                    minIdx = p;
                }
            }

            // 2. Putting mimimum to the sorted range
            if (minIdx != i) {
                int min = array[minIdx];
                array[minIdx] = array[i];
                array[i] = min;
            }
        }
        System.out.println(Arrays.toString(array));
    }

}

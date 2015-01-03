package com.alisovenko.algorithms.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * TBD: add comments for QuickSortTest.java.
 * 
 * <p>
 * Created: 18.05.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class QuickSortTest extends BaseSortTest {

    /**
     * @see com.alisovenko.algorithms.sort.BaseSortTest#sort(java.lang.Integer[])
     */
    @Override
    public void sort(Integer[] array) {
        quickSort(array, 0, array.length - 1);

    }

    public void quickSort(Integer[] array, int first, int last) {
        if (first >= last) {
            return;
        }
        
        //System.out.printf("partitioning the array: first - %s, second - %s\n", first, last);
        int r = partition(array, first, last);

        quickSort(array, first, r - 1);
        quickSort(array, r + 1, last);
    }

    /**
     * TBD
     * @param array
     * @param first
     * @param last
     * @return
     */
    private int partition(Integer[] array, int first, int last) {
        int pivotIdx = ThreadLocalRandom.current().nextInt(first, last);
        int pivot = array[pivotIdx];
        
        // 0. Swapping pivot with last element
        swap(array, pivotIdx, last);
        System.out.printf("pivot is: %s, idx: %s \n", pivot, pivotIdx);

        int d1 = first - 1;

        // 1. Moving two "windows" - the leftmost contains all elements less than pivot, rightmost - all elements bigger
        // or equal to it
        for (int d2 = first; d2 < last; d2++) {
            if (array[d2] <= pivot) {
                d1++;
                swap(array, d2, d1);
            }
        }
        
        // 2. Swapping pivot with middle element
        swap(array, d1 + 1, last);
        
        //System.out.printf("returning d1: %s\n", d1 + 1);
        return d1 + 1;
    }

}

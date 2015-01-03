package com.alisovenko.algorithms.sort;

/**
 * Test class for heapsort algorithm.
 * 
 * <p>
 * Created: 08.05.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class HeapSortTest extends BaseSortTest {

    /**
     * @see com.alisovenko.algorithms.sort.BaseSortTest#sort(java.lang.Integer[])
     */
    @Override
    public void sort(Integer[] array) {
        // 1. Create max-heap
        for (int i = array.length / 2 + 1; i >= 0; i--) {
            maxHeapify(array, i);
        }
        // 2. Sort max-heap
        for (int i = 0; i < array.length; i++) {
            // Swap the first element with the latest
            swap(array, 0, array.length - i - 1);
            maxHeapify(array, 0, array.length - i - 1);
        }
    }

    /**
     * Performs the comparison of the element <code>array[i]</code> with its left and right children. If any of them is
     * bigger than the <code>i</code> element - swaps them and goes downward the tree.
     * @param array
     * @param i
     * @param lowBoundary the lower boundary of the heap. Procedures does not operate with elements which indexes are
     *        bigger than this parameter.
     */
    private void maxHeapify(Integer[] array, int i, int lowBoundary) {
        int left, right;
        if (i == 0) {
            left = 1;
            right = 2;
        }
        else {
            left = i * 2;
            right = i * 2 + 1;
        }
        int max = i;
        if (array.length > left && array[left] > array[max]) {
            max = left;
        }
        if (array.length > right && array[right] > array[max]) {
            max = right;
        }
        if (max != i && max < lowBoundary) {
            swap(array, max, i);
            maxHeapify(array, max, lowBoundary);
        }
    }

    private void maxHeapify(Integer[] array, int i) {
        maxHeapify(array, i, array.length - 1);
    }

}

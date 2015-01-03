package com.alisovenko.algorithms.sort;

/**
 * The fast version of quick sort (http://www.vogella.com/articles/JavaAlgorithmsQuicksort/article.html)
 *
 * <p>Created: 20.05.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class QuickSortFastVersionTest extends BaseSortTest {

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
        
        
        int i = first, j = last;
        // Get the pivot element from the middle of the list
        int pivotIdx = last + (last - last)/2;
        int pivotIdx2 = (last + first) /2;
        int pivot = array[last + (last - last)/2];

        System.out.printf("pivotidx: %s; pivotidx2: %s\n", pivotIdx, pivotIdx2);
        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (array[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (array[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (first < j)
            quickSort(array, first, j);
        if (i < last)
            quickSort(array, i, last);
    }

}

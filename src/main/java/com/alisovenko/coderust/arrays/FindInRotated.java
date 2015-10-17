package com.alisovenko.coderust.arrays;

/**
 * @author alisovenko 27.12.14
 */
public class FindInRotated {
    public static int findInRotated(int[] array, int v, int l, int h) {
        if (l > h) {
            return -1;
        }
        int med = (l + h) / 2;

        if (array[med] == v) {
            return med;
        }
        // array[med] < array[h] means the right part is sorted
        if (array[med] < array[h]) {
            if (v > array[med] && v <= array[h]) {
                // the element we look for is inside the right range
                return findInRotated(array, v, med + 1, h);
            }
            // it is inside left range
            return findInRotated(array, v, l, med - 1);
        }
        else {
            // part from l to med -1 is sorted (left part)
            if (v < array[med] && v >= array[l]) {
                return findInRotated(array, v, l, med - 1);
            }
            return findInRotated(array, v, med + 1, h);
        }
    }

    public static void main(String[] args) {
        System.out.println(findInRotated(new int[]{3, 4, 5, 6, 7, 1, 2}, 4, 0, 6));
        System.out.println(findInRotated(new int[]{3, 4, 5, 6, 7, 1, 2}, 1, 0, 6));
        System.out.println(findInRotated(new int[]{1, 2, 3, 4, 5, 6, 7}, 1, 0, 6));
        System.out.println(findInRotated(new int[]{1, 2, 3, 4, 5, 6, 7}, 10, 0, 6));
    }
}

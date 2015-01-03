package com.alisovenko.coderust.arrays;

/**
 * @author alisovenko 27.12.14
 */
public class FindInRotated {
    public static int findInRotated(int[] array, int i, int l, int h) {
        if (l > h) {
            return -1;
        }
        int med = (l + h) / 2;

        if (array[med] == i) {
            return med;
        }
        if (array[med] < array[h]) {
            // we are inside sorted part from med - k to h
            if (i > array[med] && i <= array[h]) {
                // the element we look for is inside the right range
                return findInRotated(array, i, med + 1, h);
            }
            // it is inside left range
            return findInRotated(array, i, l, med - 1);
        }
        else {
            // we are inside sorted part from l to med -1
            if (i < array[med] && i >= array[l]) {
                return findInRotated(array, i, l, med - 1);
            }
            return findInRotated(array, i, med + 1, h);
        }
    }

    public static void main(String[] args) {
        System.out.println(findInRotated(new int[]{3, 4, 5, 6, 7, 1, 2}, 4, 0, 6));
        System.out.println(findInRotated(new int[]{3, 4, 5, 6, 7, 1, 2}, 1, 0, 6));
        System.out.println(findInRotated(new int[]{1, 2, 3, 4, 5, 6, 7}, 1, 0, 6));
        System.out.println(findInRotated(new int[]{1, 2, 3, 4, 5, 6, 7}, 10, 0, 6));
    }
}

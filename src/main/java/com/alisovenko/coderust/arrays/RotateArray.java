package com.alisovenko.coderust.arrays;

import java.util.Arrays;

/**
 * @author alisovenko 26.12.14
 */
public class RotateArray {
    public static int[] rotateArray(int[] array, int n) {
        n %= array.length;
        int m = n < 0 ? array.length + n : n;

        // reverse all digits
        reverseArray(array, 0, array.length - 1);

        // reverse the first chunk
        reverseArray(array, 0, m - 1);

        // reverse the second chunk
        reverseArray(array, m, array.length - 1);
        return array;
    }

    private static void reverseArray(int[] array, int from, int to) {
        for (int i = from, p = to; i < p; i++, p--) {
            int t = array[i];
            array[i] = array[p];
            array[p] = t;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(rotateArray(new int[]{2, 6, 3, 7, 3, 0}, 2)));
        System.out.println(Arrays.toString(rotateArray(new int[]{2, 6, 3, 7, 3, 0}, 9)));
        System.out.println(Arrays.toString(rotateArray(new int[]{2, 6, 3, 7, 3, 0}, -2)));
    }
}

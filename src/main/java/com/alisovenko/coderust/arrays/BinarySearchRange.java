package com.alisovenko.coderust.arrays;

import java.util.Arrays;

/**
 * @author alisovenko 20.12.14
 */
public class BinarySearchRange {
    public static int[] findKeyRange(int k, int[] array) {
        if (array.length == 0) {
            return new int[] {-1, -1};
        }

        int left = bsLeft(array, k);
        int right = bsRight(array, k);

        return new int[] {left, right};

    }

    private static int bsLeft(int[] array, int k) {
        int s = 0, e = array.length, mid = array.length / 2;

        while (true) {
            if (e < s) {
                if (array[s] == k) {
                    return s;
                }
                return -1;
            }
            if (k <= array[mid]) {
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
            mid = (s + e) / 2;
        }
    }
    private static int bsRight(int[] array, int k) {
        int s = 0, e = array.length, mid = array.length / 2;

        while (true) {
            if (e < s) {
                if (array[e] == k) {
                    return e;
                }
                return -1;
            }
            if (k >= array[mid]) {
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
            mid = (s + e) / 2;
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(findKeyRange(4, new int[]{0, 0, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 6, 7, 7, 7, 9, 9, 10})));
    }
}

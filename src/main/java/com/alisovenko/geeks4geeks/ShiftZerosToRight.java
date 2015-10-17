package com.alisovenko.geeks4geeks;

import java.util.Arrays;

/**
 * @author alisovenko 16.04.15
 */
public class ShiftZerosToRight {
    public static int[] zerosToEnd(int[] array) {
        int slow;
        int fast;

        // get to the element before first zero
        int i;
        for (i = 0; i < array.length && array[i] != 0;i++){}
        if (i == array.length) {
            // have not found any zeros
            return array;
        }
        slow = (fast = i);

        while (fast < array.length) {
            // search for next non zero element
            if (array[fast] > 0) {
                array[slow] = array[fast];
                array[fast] = 0;
                slow++;
            }
            else {
                fast++;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(zerosToEnd(new int[]{3, 1, 0, 2, 0, 0, 5, 1})));
        System.out.println(Arrays.toString(zerosToEnd(new int[]{3, 1, 5, 2, 8})));
        System.out.println(Arrays.toString(zerosToEnd(new int[]{0, 0, 0, 0})));
    }
}

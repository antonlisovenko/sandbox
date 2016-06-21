package com.alisovenko.geeks4geeks;

import java.util.Arrays;

/**
 * @author alisovenko 16.04.15
 */
public class ShiftZerosToRight {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;

        while (j < nums.length) {
            if (nums[j] == 0) {
                j++;
            } else {
                nums[i] = nums[j];
                i++;
                j++;
            }
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(zerosToEnd(new int[]{3, 1, 0, 2, 0, 0, 5, 1})));
//        System.out.println(Arrays.toString(zerosToEnd(new int[]{3, 1, 5, 2, 8})));
//        System.out.println(Arrays.toString(zerosToEnd(new int[]{0, 0, 0, 0})));
    }
}

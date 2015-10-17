package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

import java.util.Arrays;

/**
 * @author alisovenko 07.02.15
 */
public class _1_6_2 {
    private static void rotate(int[][] array ) {
        int n = array.length - 1;
        int start = 0;
        int end = array.length - 1;

        for (int i = 0; i < array.length / 2; i++) {
            for (int p = start; p < end; p++) {
                int temp = array[i][p];

                array[i][p] = array[n - p][i];
                array[n - p][i] = array[n - i][n - p];
                array[n - i][n - p] = array[p][n - i];
                array[p][n - i] = temp;
            }
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};
        rotate(arr);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

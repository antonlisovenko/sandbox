package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

import java.util.Arrays;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 *
 * Almost the same, but DO NOT USE int[] for rowsToNullify (colsToNullify) here! Use boolean arrays! Or bit vectors!
 * @author alisovenko 16.08.14
 */
public class _1_7 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 1, 0, 1, 1}, {2, 2, 2, 2, 2}, {0, 3, 3, 3, 3}, {0, 4, 4, 4, 4}};

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }

        nullify(arr);

        System.out.println();

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
    public static void nullify(int[][] arr) {
        int[] rowsToNullify = new int[arr.length];
        int[] colsToNullify = new int[arr[0].length];

        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[0].length; y++) {
                if (arr[x][y] == 0) {
                    rowsToNullify[x] = 1;
                    colsToNullify[y] = 1;
                }
            }
        }

        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[0].length; y++) {
                if (rowsToNullify[x] == 1 || colsToNullify[y] == 1) {
                    arr[x][y] = 0;
                }
            }
        }
    }
}

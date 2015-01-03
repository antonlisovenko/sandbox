package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

import java.util.Arrays;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees. Can you do this in place?
 *
 * Not bad solution, but there is the one that does this better (circular swapping)
 * @author alisovenko 16.08.14
 */
public class _1_6 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};

        rotate(arr);

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void rotate(int[][] pict) {
        int maxIdx = pict.length - 1;

        // 1. First - swap ints on the diagonale
        for (int i = 0; i < pict.length; i++) {
            for (int p = 0; p < maxIdx - i; p++) {
                swap(pict, i, p, maxIdx);
            }
        }

        // 2. Second - swap ints on x median
        for (int i = 0; i < pict.length; i++) {
            for (int p = 0; p < pict.length; p++) {
                swap2(pict, p, i, maxIdx);
            }
        }
    }

    public static void swap2(int[][] pict, int x, int y, int maxIdx) {
        int xTarget = maxIdx - x;

        int temp = pict[xTarget][y];
        pict[xTarget][y] = pict[x][y];
        pict[x][y] = temp;
    }

    public static void swap(int[][] pict, int x, int y, int maxIdx) {
        int xTarget = maxIdx - y;
        int yTarget = maxIdx - x;

        int temp = pict[xTarget][yTarget];
        pict[xTarget][yTarget] = pict[x][y];
        pict[x][y] = temp;
    }
}

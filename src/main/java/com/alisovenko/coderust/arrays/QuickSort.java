package com.alisovenko.coderust.arrays;

import java.util.Arrays;

/**
 * @author alisovenko 03.01.15
 */
public class QuickSort {
    public static void quickSort(int[] array) {
        doSort(array, 0, array.length-1);
    }

    private static void doSort(int[] array, int lo, int hi) {
        prettyPrint(array, lo, hi);
        // base case
        if (lo >= hi) {
            System.out.println("Return");
            return;
        }

        int anchor = array[lo];
        int l = lo, h = hi;


        while (l < h) {
            while (l < h && array[l] <= anchor) {
                l++;
            }
            while (l < h && array[h] > anchor) {
                h--;
            }
            if (l < h) {
                swap(l, h, array);
            }
        }
        swap(lo, l - 1, array);

        doSort(array, lo, l - 1);
        doSort(array, l, hi);
    }

    private static void prettyPrint(int[] array, int lo, int hi) {
        for (int i = 0; i < array.length; i++) {
            if (i == lo || i == hi) {
                System.out.print(" [");
                System.out.print(array[i]);
                System.out.print("] ");
            }
            else {
                System.out.print("  " + array[i] + "  ");
            }
        }
        System.out.println();
    }

    private static void swap(int x, int y, int[] array) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }

    public static void main(String[] args) {
        int[] array = {3, 6, 7, 1, 2, 7, 2, 30, 10, 3, 9};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}

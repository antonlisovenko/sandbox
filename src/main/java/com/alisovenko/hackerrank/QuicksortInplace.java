package com.alisovenko.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/19/15.
 */
public class QuicksortInplace {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();

        int[] array = new int[t];
        for (int i = 0; i < t; i++) {
            array[i] = in.nextInt();
        }
        quicksort(array);
    }

    private static void quicksort(int[] array) {
        doSorting(array, 0, array.length - 1);
    }

    private static void doSorting(int[] array, int x, int y) {
        if (x >= y) {
            return;
        }
        int p = partition(array, x, y);

        doSorting(array, x, p - 1);
        doSorting(array, p + 1, y);
    }

    private static int partition(int[] array, int x, int y) {
        int pivot = array[y];

        int i = x, j = y - 1;

        while (true) {
            while (array[i] < pivot && i <= j) {
                i++;
            }
            while (array[j] > pivot && i <= j) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(array, i, j);
        }
        swap(array, i, y);
        Arrays.stream(array).forEach(c -> System.out.print("" + c + ' '));
        System.out.println();
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

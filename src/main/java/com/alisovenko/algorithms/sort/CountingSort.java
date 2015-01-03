package com.alisovenko.algorithms.sort;

import java.util.Arrays;

/**
 * @author alisovenko 09.10.14
 */
public class CountingSort {
    private static void sort(int[] arr, int from, int to) {
        int[] temp = new int[10];
        int[] aux = new int[to - from];

        for (int i = from; i < to; i++) {
            temp[arr[i] + 1]++;
        }
        for (int i = 0; i < temp.length - 1; i++) {
            temp[i + 1] += temp[i];
        }
        for (int i = from; i < to; i++) {
            aux[temp[arr[i]]++] = arr[i];
        }
        for (int i = 0; i < aux.length; i++) {
            arr[i + from] = aux[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 1, 7, 5, 3};
        sort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{6, 1, 3, 7, 2, 1, 6};
        sort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{5, 7, 2, 4, 7, 4, 8};
        sort(arr, 3, 6);
        System.out.println(Arrays.toString(arr));

    }
}

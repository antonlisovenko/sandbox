package com.alisovenko.geeks4geeks;

import java.util.Arrays;

/**
 * @author alisovenko 06.02.15
 */
public class FindKSubsets {
    /**
     * @param arr the initial array to find k subsets
     * @param current the current k subset that we are constructing
     * @param idx the index in k subset we are on
     * @param k the size of subset
     * @param start the left border of "window"
     */
    private static void findAll(char[] arr, char[] current, int idx, int k, int start) {
        if (idx == k) {
            System.out.println(Arrays.toString(current));
            return;
        }

        for (int i = start; i < arr.length && i < arr.length - (k - idx - 1); i++ ) {
            current[idx] = arr[i];
            findAll(arr, current, idx + 1, k, i + 1);
        }
    }
    private static void findKSubsets(char[] arr, int k) {
        findAll(arr, new char[k], 0, k, 0);
    }

    public static void main(String[] args) {
        findKSubsets("abcdef".toCharArray(), 2);
    }
}

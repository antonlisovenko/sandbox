package com.alisovenko.geeks4geeks;

import java.util.Arrays;

/**
 * @author alisovenko 12.04.15
 */
public class PancakeSorting {
    public static int[] sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int smallest = i;
            for (int p = i; p < a.length; p++) {
                if (a[p] < a[smallest]) {
                    smallest = p;
                }
            }
            if (a[smallest] < a[i]) {
                flip(a, smallest);
                flip(a, i);
            }
        }
        return a;
    }

    private static void flip(int[] a, int idx) {
        int s = idx;
        int e = a.length - 1;
        while (s < e) {
            int t = a[e];
            a[e] = a[s];
            a[s] = t;
            s++;
            e--;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{4, 6, 2, 6, 6, 2, 8, 44, 2, 1, 11})));
        System.out.println(Arrays.toString(sort(new int[]{88, 1, 22, 42, 11, 0, 50})));
    }
}

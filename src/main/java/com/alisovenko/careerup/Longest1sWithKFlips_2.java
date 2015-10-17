package com.alisovenko.careerup;

import java.util.Arrays;

/**
 * @author alisovenko 18.01.15
 */
public class Longest1sWithKFlips_2 {
    public static int[] findMaxFlipped(int[] ar, int m) {
        int l = 0, r = 0;
        int lMax = 0;
        int zerosCnt = m;
        int maxLength=0;

        while (r < ar.length) {
            if (zerosCnt >= 0) {
                if (ar[r] == 0) {
                    zerosCnt--;
                }
                r++;
                if (maxLength < r - l) {
                    maxLength = r - l;
                    lMax = l;
                }
            }
            else {
                if (ar[l] == 0) {
                    zerosCnt++;
                }
                l++;
            }
        }

        int[] result = new int[m];
        int p = 0;
        for (int i = lMax; i < ar.length && p < m; i++) {
            if (ar[i] == 0) {
                result[p++] = i;
            }
        }
        return result;

    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findMaxFlipped(new int[] {1, 1, 0, 1, 1, 0, 0, 1, 1, 1 }, 1)));
        System.out.println(Arrays.toString(findMaxFlipped(new int[] {1, 1, 0, 1, 1, 0, 0, 1, 1, 1 }, 2)));
    }
}

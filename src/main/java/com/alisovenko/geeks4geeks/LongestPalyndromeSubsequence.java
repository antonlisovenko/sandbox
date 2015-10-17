package com.alisovenko.geeks4geeks;

import com.alisovenko.base.Utils;

/**
 * @author alisovenko 09.01.15
 */
public class LongestPalyndromeSubsequence {
    private static int findLongestPalindromeSubsequence(char[] str, int lo, int hi) {
        if (hi==lo) {
            return 1;
        }
        if (hi - lo == 1 && str[hi] == str[lo]) {
            return 2;
        }
        if (str[lo] == str[hi]) {
            return findLongestPalindromeSubsequence(str, lo + 1, hi - 1) + 2;
        }
        return Math.max(findLongestPalindromeSubsequence(str, lo + 1, hi), findLongestPalindromeSubsequence(str, lo, hi - 1));
    }

    private static int findLongestPalindromeSubsequenceDP(char[] str) {
        int[][] cache = new int[str.length][str.length];

        // diagonale (subsequence length for the same letter is 1)
        for (int i = 0; i < cache.length; i++) {
            cache[i][i] = 1;
        }

        // length of range
        for (int i = 1; i < str.length; i++) {
            for (int p = 0; p < str.length - i; p++) {
                int s = p + i;

                if (i == 1 && str[p] == str[s]) {
                    cache[p][s] = 2;
                }
                else {
                    if (str[p] == str[s]) {
                        cache[p][s] = cache[p + 1][s - 1] + 2;
                    }
                    else {
                        cache[p][s] = Math.max(cache[p + 1][s], cache[p][s - 1]);
                    }
                }
            }
        }
        Utils.printMatrix(cache, new String(str), new String(str));

        return cache[0][str.length - 1];
    }

    public static void main(String[] args) {
//        char[] ch = "GEEKSFORGEEKS".toCharArray();
        char[] ch = "HEEPHT".toCharArray();
        System.out.println(findLongestPalindromeSubsequence(ch, 0, ch.length - 1));
        System.out.println(findLongestPalindromeSubsequenceDP(ch));
    }
}

package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/29/16.
 */
public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;

        int[] similar = new int[n + 1];
        int[] diff = new int[n + 1];

        similar[0] = 0;
        diff[0] = k;

        for (int i = 1; i < n; i++) {
            similar[i] = diff[i - 1];
            diff[i] = (similar[i - 1] + diff[i - 1]) * (k - 1);
        }
        return similar[n - 1] + diff[n - 1];
    }
}

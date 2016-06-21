package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         2/6/16.
 */
public class HIndex {
    public int hIndex(int[] citations) {
        if (citations.length == 0) return 0;

        Arrays.sort(citations);

        int h = 0;

        for (int i = 0; i < citations.length; i++) {
            int c = citations[i];
            if (citations.length - i <= c) h = Math.max(h, citations.length - i);
        }
        return h;
    }
}

package com.alisovenko.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alisovenko
 *         3/2/16.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";

        int[] map = new int[255];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }
        int lo = 0, hi = 0, minLo = 0, minHi = Integer.MAX_VALUE, count = t.length();

        while (hi < s.length()|| (count == 0 && lo < s.length())) {
            // we are having all letters in our window - decreasing left border
            if (count == 0) {
                if (minHi - minLo > hi - lo) {
                    minLo = lo;
                    minHi = hi;
                }
                char c = s.charAt(lo++);
                if (++map[c] > 0) count++;
            } else {
                char c = s.charAt(hi++);
                if (map[c]-- > 0) count--;
            }
        }
        return minHi < Integer.MAX_VALUE ? s.substring(minLo, minHi) : "";
    }
}

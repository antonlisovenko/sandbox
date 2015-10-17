package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko 23.01.15
 */
public class LCP {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return null;

        Arrays.sort(strs);

        int maxLength = 0;
        int index = -1;
        for (int i = 0; i < strs.length - 1; i++) {
            String f = strs[i];
            String s = strs[i+1];

            int lcpLength = lcpLength(f, s);
            if (maxLength < lcpLength) {
                maxLength = lcpLength;
                index = i;
            }
        }

        if (index < 0) {
            return null;
        }
        return strs[index].substring(0, maxLength);

    }

    private int lcpLength(String f, String s) {
        int result = 0;
        for (int i = 0; i < Math.min(f.length(), s.length()); i++) {
            if (f.charAt(i) != s.charAt(i)) break;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        new LCP().longestCommonPrefix(new String[]{"c", "c"});
    }
}

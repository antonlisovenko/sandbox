package com.alisovenko.leetcode;

/**
 * @author alisovenko 23.01.15
 */
public class LCP_2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        int minLength = Integer.MAX_VALUE;

        for (String s: strs) {
            minLength = Math.min(s.length(), minLength);
        }

        int length = -1;

        outerloop:
        for (int i = 0; i < minLength; i++) {
            length = i;
            for (String s: strs) {
                if (s.charAt(i) != strs[0].charAt(i)) {
                    length = i - 1;
                    break outerloop;
                }
            }
        }
        if (length < 0) {
            return "";
        }
        return strs[0].substring(0, length + 1);

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
        System.out.println(new LCP_2().longestCommonPrefix(new String[]{"c", "c"}));
    }
}

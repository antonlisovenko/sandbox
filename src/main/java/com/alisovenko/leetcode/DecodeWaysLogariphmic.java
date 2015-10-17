package com.alisovenko.leetcode;

/**
 * @author alisovenko 27.01.15
 */
public class DecodeWaysLogariphmic {
    public int numDecodings(String s) {
        return decode(s, 0, s.length()-1);
    }
    private int decode(String s, int l, int h) {
        if (l > h) {
            // base case 1
            return 0;
        }
        if (l == h) {
            return isDecodable(s.charAt(l)) ? 1 : 0;
        }
        int med = l + (h - l)/ 2;
        int c = decode(s, l, med) + decode(s, med + 1, h);
        c += findInIntersection(s, med, med + 1);
        return c;
    }

    private int findInIntersection(String s, int l, int h) {
        if (s.length() >= h) {
            return 0;
        }
        return isDecodable(s.substring(l, h + 1)) ? 1 : 0;
    }
    private boolean isDecodable(char c) {
        return '1' <= c && c <= '9';
    }
    private boolean isDecodable(String c) {
        return c.compareTo("10") >= 0 && c.compareTo("26") <= 0;
    }
}

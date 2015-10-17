package com.alisovenko.leetcode;

/**
 * @author alisovenko 15.03.15
 */
public class ZigZagString {
    public String convert(String s, int nRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder[] sb = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i++) {
            sb[i] = new StringBuilder();
        }

        int gap = 2 * nRows - 2;
        /*for (int i = 0; i < Math.max(1, s.length() - gap); i++) {
            for (int k = 0; k < Math.min(s.length(), nRows); k++) {
                sb[k].append(s.charAt(i + k));
                if (k > 0 && k < nRows - 1 && gap - k < s.length()) {
                    sb[k].append(s.charAt(i + gap - k));
                }
            }
        }*/

        for (int i = 0; i < s.length(); i++) {
            int pos = i % gap;
            if (pos < nRows) {
                sb[pos].append(s.charAt(i));
            }
            else {
                int t = gap - pos;
                sb[t].append(s.charAt(i));
            }
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder t : sb) {
            res.append(t);
        }
        return res.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new ZigZagString().convert("ABC", 2));
//        System.out.println(new ZigZagString().convert("A", 3));
        System.out.println(new ZigZagString().convert("ABCDEFJ", 3));
    }
}

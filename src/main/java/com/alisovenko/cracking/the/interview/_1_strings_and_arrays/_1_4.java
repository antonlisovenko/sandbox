package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficient space at
 * the end of the string to hold the additional characters, and that you are given the "true" length of the string.
 * (Note: if implementing in Java, please usea character array so that you can perform this operation inplace.)
 *
 * Partly correct: we could avoid arraycopy by moving FROM LAST TO FIRST characters in array
 *
 * @author alisovenko 10.08.14
 */
public class _1_4 {
    public static void main(String[] args) {
        String s = encodeWhitespaces("Mr  John Smith    ".toCharArray(), 14);
        assert s.equals("Mr%20John%20Smith") : "result: " + s;
    }

    public static String encodeWhitespaces(char[] in, int trueWidth) {
        int start = in.length - trueWidth;
        // shift
        System.arraycopy(in, 0, in, start, trueWidth);

        int p = 0;
        boolean insideWhitespaces = false;
        for (int i = start; i < in.length; i++) {
            if (in[i] == ' ') {
                if (!insideWhitespaces) {
                    in[p++] = '%';
                    in[p++] = '2';
                    in[p++] = '0';
                }
                insideWhitespaces = true;
            } else {
                insideWhitespaces = false;
                in[p++] = in[i];
            }
        }

        return new String(in);
    }
}

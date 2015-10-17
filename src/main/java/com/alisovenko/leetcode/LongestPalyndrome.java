package com.alisovenko.leetcode;

/**
 * @author alisovenko 13.03.15
 */
public class LongestPalyndrome {
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int from = 0, to = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            // Case 1: the character is at the center of palindrome
            if (idx > 0 && idx < s.length() - 1) {
                for (int i = idx - 1, p = idx + 1; i >= 0 && p < s.length(); i--, p++) {
                    if (s.charAt(i) != s.charAt(p))
                        break;
                    if (p - i > to - from) {
                        from = i;
                        to = p;
                    }
                }
            }

            // Case 2: character is the same as its neighbour
            if (idx < s.length() -1) {
                for (int i = idx, p = idx + 1; i >= 0 && p < s.length(); i--, p++) {
                    if (s.charAt(i) != s.charAt(p))
                        break;
                    if (p - i > to - from) {
                        from = i;
                        to = p;
                    }
                }
            }
        }

        return s.substring(from, to + 1);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalyndrome().longestPalindrome("ccc"));
    }
}

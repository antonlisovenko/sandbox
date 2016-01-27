package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         12/21/15.
 */
public class StrIsPalindromeCodePoint {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.trim().length() == 0) return true;

        int i = 0, f = s.codePointCount(0, s.length()) - 1;
        while (true) {
            while (!Character.isAlphabetic(s.codePointAt(i))) i += Character.charCount(s.codePointAt(i));
            while (!Character.isAlphabetic(s.codePointAt(f))) f -= Character.charCount(s.codePointAt(f));

            if (i >= f || s.codePointAt(i) != s.codePointAt(f)) break;

            i += Character.charCount(s.codePointAt(i));
            f -= Character.charCount(s.codePointAt(f));
        }

        if (i >= f) return true;
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new StrIsPalindromeCodePoint().isPalindrome("abc cba "));
//        System.out.println(new StrIsPalindromeCodePoint().isPalindrome("ab c cba "));
//        System.out.println(new StrIsPalindromeCodePoint().isPalindrome("ab c cbas "));
        int x = 0xD800DC00, y = 0x20801;
        System.out.println(Character.isValidCodePoint(x));
        System.out.println(Character.isAlphabetic(x));
        System.out.println(Character.isValidCodePoint(y));
        System.out.println(new StrIsPalindromeCodePoint().isPalindrome(new String(
                new char[]{Character.lowSurrogate(x), Character.highSurrogate(x), ' ', Character.lowSurrogate(x), Character.highSurrogate(x)})));
    }
}

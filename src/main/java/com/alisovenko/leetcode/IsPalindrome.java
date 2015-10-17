package com.alisovenko.leetcode;

/**
 * @author alisovenko 28.03.15
 */
public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        long s = x;
        if (x < 0)
            s = Math.abs((long)x);

        long swaped = 0;
        long t = s;

        while (t > 0) {
            swaped = swaped * 10 + t % 10;
            t /= 10;
        }

        while (s > 0) {
            if (s % 10 != swaped % 10) {
                return false;
            }
            s /= 10;
            swaped /= 10;
        }

        return true;
    }

    public static void main(String[] args) {
//        System.out.println(isPalindrome(2224222));
//        System.out.println(isPalindrome(12344321));
//        System.out.println(isPalindrome(-2345432));
        System.out.println(isPalindrome(2147447412));
        System.out.println(isPalindrome(-2147447412));
    }
}

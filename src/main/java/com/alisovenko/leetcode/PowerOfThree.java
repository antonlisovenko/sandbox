package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/27/16.
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        // 1. brute-force: devide on 3 until reach zero
        // 2. optimized recursion: 3^8 = 3^4 * 3^4 = 3^2 * 3^2 * 3^2 * 3^2
        if (n <= 2) return false;

        int s = n;
        while (s > 0) {
            if (s % 3 != 0) return false;
            s /= 3;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new PowerOfThree().isPowerOfThree(27));
    }
}

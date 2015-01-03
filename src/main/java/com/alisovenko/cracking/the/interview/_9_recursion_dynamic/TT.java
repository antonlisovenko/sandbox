package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

/**
 * @author alisovenko 07.12.14
 */
public class TT {
    private static int getNumberOfWays(int n, int[] cache) {
        if (n >= 0 && cache[n] > 0) {
            return cache[n];
        }

        int result;
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            result = 1;
        }
        else {
            result = getNumberOfWays(n -1, cache) + getNumberOfWays(n-2, cache) + getNumberOfWays(n-3, cache);
        }

        if (n > 0)
            cache[n] = result;

        return result;
    }

    public static void main(String[] args) {
        int steps = 8;
        System.out.println(getNumberOfWays(steps, new int[steps + 1]));
    }
}

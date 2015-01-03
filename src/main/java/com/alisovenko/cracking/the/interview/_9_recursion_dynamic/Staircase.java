package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

/**
 * A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 *
 * @author alisovenko 12.10.14
 */
public class Staircase {
    public static int numberOfWays(int n, int[] cache) {
        System.out.println("N: " + n);
        if (n < 0) {
            System.out.println("Return 0");
            return 0;
        }
        if (n == 0) {
            System.out.println("Return 1");
            return 1;
        }
        if (cache[n] >= 0) {
            return cache[n];
        }
        int n_1 = numberOfWays(n - 1, cache);
        int n_2 = numberOfWays(n - 2, cache);
        int n_3 = numberOfWays(n - 3, cache);
        System.out.println("n-1 : " + n_1 + ", n-2 : " + n_2 + ", n-3 : " + n_3);

        int r = (n_1  + n_2 + n_3);
        System.out.println("r: " + r);
        cache[n] = r;
        return r;
    }

    public static int numberOfWaysBottomUp(int n) {
        int cache[] = new int[n];

        cache[0] = 1;
        cache[1] = 2;
        cache[2] = 4;

        for (int i = 3; i < n; i++) {
            cache[i] = cache[i-1] + cache[i-2] + cache[i-3];
        }
        return cache[n - 1];
    }

    public static void main(String[] args) {
        int[] cache = new int[10];
        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }

        System.out.println("Result: " + numberOfWays(8, cache));
        System.out.println("Result: " + numberOfWaysBottomUp(8));
    }
}

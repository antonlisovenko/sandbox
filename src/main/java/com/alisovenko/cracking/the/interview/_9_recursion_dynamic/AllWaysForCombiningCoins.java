package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

/**
 * Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent),
 * write code to calculate the number of ways of representing n cents.
 *
 * @author alisovenko 17.12.14
 */
public class AllWaysForCombiningCoins {
    private static int allWays(int N, int idx, int max) {
        if (idx > N) {
            return 0;
        }
        if (idx == N) {
            return 1;
        }

        int result = 0;

        // if we came to this recursion frame from "smaller" invocation (allWays(N, idx + 1, 1)) we need to avoid
        // splitting the tail to 25 or 10 pieces as this leads to permutations, but we need combinations
        if (max >= 25)
            result = allWays(N, idx + 25, 25);

        if (max >= 10)
            result += allWays(N, idx + 10, 10);

        if (max >= 5)
            result += allWays(N, idx + 5, 5);

        if (max >= 1)
            result += allWays(N, idx + 1, 1);

        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.printf("%d -> %d\n", i, allWays(i, 0, 100));
        }
    }

    private static int allWaysWrong(int N) {
        int[] cache = new int[N + 1];
        cache[0] = 1;

        for (int i = 1; i <= N; i++) {
            int ways = 0;
            for (int p = 1; p <= i; p++) {
                ways = cache[p - 1];
                if (p >= 5) {
                    ways += cache[p - 5];
                }
                if (p >= 10) {
                    ways += cache[p - 10];
                }
                if (p >= 25) {
                    ways += cache[p - 25];
                }
            }
            cache[i] = ways;
        }
        return cache[N];
    }
}

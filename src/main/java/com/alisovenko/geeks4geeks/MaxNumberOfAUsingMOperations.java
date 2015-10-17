package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 16.03.15
 */
public class MaxNumberOfAUsingMOperations {
    public static int maxNumberOfA(int n) {
        // dp[i]: max number of A for i number of operations
        int[] dp = new int[n + 1];

        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int o1 = dp[i - 1] + 1;
            int o2 = -1;

            for (int p = 3; i - p > 0; p++) {
                o2 = Math.max(o2, dp[i - p] * (p - 1));
            }
            dp[i] = Math.max(o1, o2);

        }

        return dp[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 15; i++) {
            System.out.printf("%d: %d\n", i, maxNumberOfA(i));
        }
    }
}

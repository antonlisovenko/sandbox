package com.alisovenko.hackerrank;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         6/20/16.
 */
public class CoinChange {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int coinsSize = s.nextInt();
        int[] coins = new int[coinsSize];
        for (int i = 0; i < coinsSize; i++)
            coins[i] = s.nextInt();

        Stopwatch stopwatch = Stopwatch.createStarted();
        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int p = 0; p < coinsSize; p++) {
            for (int i = coins[p]; i <= N; i++) {
                dp[i] += dp[i - coins[p]];
            }
        }
        System.out.println(dp[N]);
        System.out.println(stopwatch.stop());

    }
}

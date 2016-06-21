package com.alisovenko.hackerrank;

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

        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int p = 0; p < coinsSize; p++) {
            System.out.printf("Coin: %d:\n", coins[p]);
            for (int i = coins[p]; i <= N; i++) {
                dp[i] += dp[i - coins[p]];
            }
            System.out.println(Arrays.toString(dp));
        }
        System.out.println("==============================");
        long[] dp2 = new long[N + 1];
        dp2[0] = 1;
        for (int i = 1; i <= N; i++) {
            System.out.printf("Step: %d:\n", i);
            for (int p = 0; p < coinsSize && coins[p] <= i; p++)
                dp2[i] += dp2[i - coins[p]];
            System.out.println(Arrays.toString(dp2));
        }


    }

}

package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/19/16.
 */
public class BestTimeToBuyAndSellStocksWithCooldown {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Max profit for (0, i) range
        int[] dp = new int[n];

        for (int i = 1; i < prices.length; i++) {
            // if both current and previous segment are growing - just adding the new growth
            if ((i > 1 && prices[i - 1] > prices[i - 2] && prices[i] > prices[i - 1])
                    || i == 1
                    || prices[i] <= prices[i - 1]) {
                dp[i] = dp[i - 1] + Math.max(0, prices[i] - prices[i - 1]);
            } else {
                for (int p = 0; p <= i; p++) {
                    int c = prices[i] - prices[p];
                    if (p > 1) c += dp[p - 2];
                    dp[i] = Math.max(dp[i], c);
                }
            }
        }
        return n > 0 ? dp[n - 1] : 0;
    }
}

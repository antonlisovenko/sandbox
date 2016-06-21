package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/19/16.
 */
public class BestTimeToBuyAndSellStocksWithCooldown3 {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];

        buy[0] = 0;
        sell[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(i > 1 ? sell[i - 2] - prices[i] : 0, buy[i - 1]);
            sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
            System.out.printf("%d -> buy: %s, sell: %s, (%d)\n", i, buy[i], sell[i], prices[i]);
        }
        return sell[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{0, 3, 2, 4, 2, 6, 1, 8, 10}));
    }
}

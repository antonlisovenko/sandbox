package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/19/16.
 */
public class BestTimeToBuyAndSellStocksWithCooldown2 {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int len = prices.length;
        int[] sell = new int[len]; // the max profit for any sequence end with sell
        int[] coolDown = new int[len]; // the mas profit for any sequence end with cool down
        sell[0] = 0;
        coolDown[0] = 0;
        for (int i = 1; i < len; i++) {
            sell[i] = Integer.max(sell[i - 1] + prices[i] - prices[i - 1], coolDown[i - 1]);
            coolDown[i] = Integer.max(sell[i - 1], coolDown[i - 1]);
            System.out.printf("sell[%d]: %s, coolDown[%d]: %s, (%d)\n", i, sell[i], i, coolDown[i], prices[i]);
        }
        return Integer.max(sell[len - 1], coolDown[len - 1]);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{0, 3, 2, 4, 3, 6, 1, 8, 10}));
    }
}

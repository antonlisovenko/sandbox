package com.alisovenko.cormen;

/**
 * Dynamic programming: find the optimum way to cut the rod.
 * <p>
 * Each cut has the specific price. We need to find the most valuable way to cut (or not to cut at all)
 * <p>
 * Straightforward solution: at each iteration find the maximum of all combinations of cut (1/2...n inches) and the tail (subproblems of n - i)
 *
 * @author alisovenko 03.12.14
 */
public class CutTheRod {
    private static int iteration = 0;

    private static int maxValue(int[] prices, int remainder) {
        if (remainder == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < remainder; i++) {
            max = Math.max(max, prices[i] + maxValue(prices, remainder - i - 1));
        }
        System.out.printf("Iteration: %d, Remainder: %d, max: %d \n", iteration++, remainder, max);
        return max;
    }

    private static int maxValueTopBottom(int[] prices, int remainder, int[] cached) {
        if (remainder == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < remainder; i++) {
            int val = cached[remainder - i - 1];
            if (val == 0) {
                val = maxValueTopBottom(prices, remainder - i - 1, cached);
            }
            max = Math.max(max, prices[i] + val);
        }
        cached[remainder] = max;
        System.out.printf("Iteration: %d, Remainder: %d, max: %d \n", iteration++, remainder, max);
        return max;
    }

    private static int maxValueBottomTop(int[] prices) {
        int[] cached = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int max = 0;
            for (int j = 0; j <= i; j++) {
                max = Math.max(prices[j], cached[i - j]);
            }
            cached[i] = max;
        }
        return cached[prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        System.out.println(maxValue(prices, prices.length));
        iteration = 0;
        System.out.println("----------");
        System.out.println(maxValueTopBottom(prices, prices.length, new int[prices.length + 1]));

        System.out.println("----------");
        System.out.println(maxValueBottomTop(prices));
    }
}

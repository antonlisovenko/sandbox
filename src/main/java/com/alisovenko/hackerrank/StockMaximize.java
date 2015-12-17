package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/21/15.
 */
public class StockMaximize {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] stocks = new int[n];
            for (int j = 0; j < n; j++) {
                stocks[j] = in.nextInt();
            }

            System.out.println(findMaxBenefit(stocks));
        }
    }

    private static long findMaxBenefit(int[] stocks) {
        int[] maxToTheRight = new int[stocks.length];
        maxToTheRight[stocks.length - 1] = stocks[stocks.length - 1];

        for (int i = stocks.length - 2; i >=0 ; i--) {
            maxToTheRight[i] = Math.max(stocks[i], maxToTheRight[i + 1]);
        }

        long benefit = 0;
        for (int i = 0; i < stocks.length; i++) {
            benefit += Math.max(maxToTheRight[i] - stocks[i], 0);
            if (benefit < 0) {
                System.out.println();
            }
        }
        return benefit;
    }
}

package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import com.google.common.base.Stopwatch;

/**
 * @author alisovenko
 *         6/24/16.
 */
public class CoinsChange {
    public static int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length - 1) return 1; // one denom remaining -> one way to do it
        int denomAmount = denoms[index];
        int ways = 0;
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1); // go to next denom
        }
        return ways;
    }

    public static int makeChange1(int n) {
        int[] denoms = {25, 10, 5, 1};
        return makeChange(n, denoms, 0);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(makeChange1(100000));
        System.out.println(stopwatch.stop());
    }
}

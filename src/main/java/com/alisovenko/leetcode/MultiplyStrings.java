package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         3/1/16.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) return null;

        int n = num1.length();
        int m = num2.length();

        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        int[] ans = new int[n + m];

        for (int i = 0; i < n; i++) {
            int rest = 0;
            int sumRest = 0;
            int j;
            for (j = 0; j < m; j++) {
                int p = (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
                p += rest;
                int newSum = ans[i + j] + p % 10 + sumRest;
                ans[i + j] = newSum % 10;

                sumRest = newSum / 10;
                rest = p / 10;
            }
            if (rest > 0 || sumRest > 0) ans[i + j] = rest + sumRest;
        }

        // Removing leading zero if it exists
//        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) ans.remove(ans.size() - 1);

        // Turning ans list to strings
        StringBuilder sb = new StringBuilder();
        for (int i = ans.length - 1; i >= 0; i--) {
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MultiplyStrings().multiply("123456789", "987654321"));
    }
}

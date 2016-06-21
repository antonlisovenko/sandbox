package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko
 *         1/17/16.
 */
public class StrobogrammaticNumber3 {
    public int strobogrammaticInRange(String low, String high) {
        int r = 0;
        for (int i = low.length(); i <= high.length(); i++) {
            r += findStrobogrammatic(i, low, high);
        }
        return r;
    }

    public int findStrobogrammatic(int n, String low, String high) {
        return findAllPermutations(0, n, new char[n], low, high);
    }

    private int findAllPermutations(int k, int n, char[] current, String low, String high) {
        // Base case: we can dump the current sequence
        if (k == (n + 1) / 2) {
            if (n > low.length() && n < high.length()) return 1;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) sb.append(current[i]);
            for (int i = n/2 - 1; i >= 0; i--) sb.append(current[i] == '9' ? '6' : (current[i] == '6' ? '9' : current[i]));
            String s = sb.toString();
            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length() && s.compareTo(high) > 0)) return 0;
            return 1;
        }
        int res = 0;
        if (k != 0 || n == 1) {
            current[k] ='0';
            res += findAllPermutations(k + 1, n, current, low, high);
        }

        current[k] = '1';
        res += findAllPermutations(k + 1, n, current, low, high);

        current[k] = '8';
        res += findAllPermutations(k + 1, n, current, low, high);

        // Edge case: we shouldn't add '6' and '9' as the middle element for odd-length numbers
        if (n % 2 == 0 || k != n / 2) {
            current[k] = '6';
            res += findAllPermutations(k + 1, n, current, low, high);

            current[k] = '9';
            res += findAllPermutations(k + 1, n, current, low, high);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber3().strobogrammaticInRange("50", "100"));
    }
}

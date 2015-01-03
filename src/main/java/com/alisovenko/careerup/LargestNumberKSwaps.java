package com.alisovenko.careerup;

import java.util.*;

/**
 * http://www.careercup.com/question?id=5637944224251904
 *
 * @author alisovenko 05.10.14
 */
public class LargestNumberKSwaps {
    public static int find(int input, int k) {
        List<Integer> digits = new ArrayList<>();
        int n = input;
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        Collections.reverse(digits);

        int numberOfSwaps = 0, idx = 0;
        while (numberOfSwaps != k) {
            int pos = findLastBiggest(digits, idx);
            if (pos != idx) {
                // We managed to find the next number that is bigger than current
                swap(digits, idx, pos);
                numberOfSwaps++;
            }
            idx++;
        }
        return arrayToInt(digits);
    }



    private static int findLastBiggest(List<Integer> digits, int from) {
        int max = 0, maxPos = 0;
        for (int i = from; i < digits.size(); i++) {
            // Note, that latest max overrides the previous one even if they are equal
            if (digits.get(i) >= max) {
                max = digits.get(i);
                maxPos = i;
            }
        }
        // In case that we already on maximum - return the current pos
        if (max == digits.get(from)) {
            return from;
        }
        return maxPos;
    }

    private static void swap(List<Integer> digits, int from, int to) {
        int t = digits.get(from);
        digits.set(from, digits.get(to));
        digits.set(to, t);
    }

    private static int arrayToInt(List<Integer> digits) {
        int result = 0;
        for (int i : digits) {
            result = result * 10 + i;
        }
        return result;
    }

    public static void main(String[] args) {
        assert (find(34155, 2) == 55143);
        assert (find(12520, 2) == 52210);
        int i = find(8799, 2);
        System.out.println(i);
        assert (i == 9987);
    }
}

package com.alisovenko.algorithms.others;

/**
 * @author alisovenko
 *         4/1/16.
 */
public class TextXor {
    public static void main(String[] args) {
        int res = 0;
        for (int i : new int[]{1, 6, 2, 6, 7, 1, 7, 3}) {
            res ^= i;
        }
        System.out.println(res);
    }
}

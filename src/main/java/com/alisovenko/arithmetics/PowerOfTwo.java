package com.alisovenko.arithmetics;

/**
 * @author alisovenko
 *         3/7/16.
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int p = (int) Math.pow(2, i);
            System.out.printf("2^%d: %s\n", i, Integer.toBinaryString(p));
        }
    }
}

package com.alisovenko.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author alisovenko 19.10.14
 */
public class dd {
    public static int reverseTheNumber(int n) {
        int r = 0;

        while (n > 0) {
            r *= 10;
            r += n % 10;
            n /= 10;
        }
        return r;
    }

    private static int sum = 0;

    public static int reverseTheNumberR(int n) {
        if (n <= 0) {
            return sum;
        }
        sum *= 10;
        sum += n % 10;
        return reverseTheNumberR(n / 10);
    }

    public static void printNext(int current, int to) {
        // base case
        if (current > to) {
            return;
        }

        System.out.println(current);
        printNext(++current, to);
    }

    public static void main(String[] args) {
        System.out.println(reverseTheNumber(34234));
        System.out.println(reverseTheNumberR(34234));
        printNext(2, 8);
    }

}

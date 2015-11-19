package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/17/15.
 */
public class TimeConversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        String next = in.next();

        String ampm = next.substring(next.length() - 2);
        if (ampm.equalsIgnoreCase("am")) {
            System.out.println(next.substring(0, next.length() - 2));
            return;
        }
        int hours = Integer.parseInt(next.substring(0, 2));

        System.out.println(String.valueOf((int)hours + 12) + next.substring(2, next.length() - 2));
    }
}

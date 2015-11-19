package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/17/15.
 */
public class Staircase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        int n = Integer.valueOf(in.next());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}

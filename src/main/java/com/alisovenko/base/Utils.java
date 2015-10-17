package com.alisovenko.base;

import java.util.List;

/**
 * @author alisovenko 16.12.14
 */
public class Utils {

    private static int c= 0;

    public static void startFrame(String txt) {
        c++;
        pad();
        System.out.println(txt);
    }

    public static void endFrame(String txt) {
        c--;
        pad();
        System.out.println(txt);
    }
    private static void pad() {
        for (int i = 0; i < c; i++) {
            System.out.print('.');
        }
        System.out.print(" ");
    }

    public static void printMatrix(int[][] m) {
        for (final int[] ints : m) {
            for (final int i : ints) {
                System.out.printf("%2d", i);
            }
            System.out.println();
        }
    }
    public static void printMatrix(int[][] m, String rowHead, String colHead) {
        System.out.print("  ");

        for (final char c : rowHead.toCharArray()) {
            System.out.printf("%-2s", c);
        }
        System.out.println();

        int p = 0;
        for (final int[] ints : m) {
            System.out.printf("%-2s", colHead.charAt(p++));

            for (final int i : ints) {
                System.out.printf("%-2d", i);
            }
            System.out.println();
        }
    }
}

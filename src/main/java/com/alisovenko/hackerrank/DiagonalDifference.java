package com.alisovenko.hackerrank;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/17/15.
 */
@SuppressWarnings("ALL")
public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        String a;
        int n = Integer.parseInt(in.next());
        int sum1 = 0, sum2 = 0;
        System.out.println("n: " + n);
        for (int i = 0; i < n; i++) {
            int[] r = Arrays.stream(in.next().split(" ")).mapToInt(Integer::valueOf).toArray();
            sum1 += r[i];
            sum2 += r[n - i - 1];
            System.out.println(Arrays.toString(r));
        }
        System.out.println(Math.abs(sum1 - sum2));
    }
}

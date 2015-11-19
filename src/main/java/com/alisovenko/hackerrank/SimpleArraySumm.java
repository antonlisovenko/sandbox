package com.alisovenko.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/17/15.
 */
public class SimpleArraySumm {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        String a;
        a = in.next();
        a = in.next();
        String[] tokens = a.split(" ");
        System.out.println(Arrays.toString(tokens));
        System.out.println(Arrays.stream(tokens).mapToInt(s -> Integer.valueOf(s)).sum());
    }
}

package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/18/15.
 */
public class TwoStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();
        outerLoop:
        for (int i = 0; i < t; i++) {
            char[] s1 = in.next().toCharArray();
            char[] s2 = in.next().toCharArray();

            boolean[] bits1 = new boolean[1<<16];

            for (char c : s1) {
                bits1[c - 'a'] = true;
            }

            for (char c : s2) {
                if (bits1[c - 'a']) {
                    System.out.println("YES");
                    continue  outerLoop;
                }
            }
            System.out.println("NO");
        }
    }
}

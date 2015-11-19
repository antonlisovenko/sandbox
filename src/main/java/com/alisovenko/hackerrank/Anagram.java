package com.alisovenko.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/18/15.
 */
public class Anagram {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            String str = in.next();

            // Odd length -> no anagram possible
            if (str.length() == 0 || ((str.length() & 1) == 1)) {
                System.out.println("-1");
            }
            else {
                char[] left = str.substring(0, str.length() / 2).toCharArray();
                char[] right = str.substring(str.length() / 2, str.length()).toCharArray();

                int diff = 0;
                int[] histo = new int['z' - 'a' + 1];
                for (char c : left) {
                    histo[c - 'a']++;
                }
                for (char c : right) {
                    if (histo[c - 'a'] > 0) histo[c - 'a']--;
                }
                for (int h : histo) {
                    diff += h;
                }
                System.out.println(diff);
            }
        }
    }
}

package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/19/15.
 */
public class PalindromeIndex {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            String str = in.next();

            boolean found = false;
            for (int j = 0, p = str.length() - 1; j <= p; j++, p--) {
                if (str.charAt(j) != str.charAt(p)) {
                    if (checkPalyndrom(str, j+1, p)) {
                        System.out.println(j);
                    }
                    else {
                        System.out.println(p);
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("-1");
            }
        }
    }

    private static boolean checkPalyndrom(String str, int i, int l) {
        for (int j = i, p = l; j <= p; j++, p--) {
            if (str.charAt(j) != str.charAt(p)) {
                return false;
            }
        }
        return true;
    }
}

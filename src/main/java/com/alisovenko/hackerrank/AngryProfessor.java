package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         11/17/15.
 */
public class AngryProfessor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int late = 0;
            int n = in.nextInt();
            int k = in.nextInt();
            for (int j = 0; j < n; j++) {
                if (in.nextInt() > 0) late++;
            }
            if (n - late < k) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}

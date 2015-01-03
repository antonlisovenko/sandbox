package com.alisovenko.cormen;

import java.util.Stack;

/**
 * Longest common sequence (LCS)
 *
 * @author alisovenko 05.12.14
 */
public class LCS {
    /**
     * Straighforward method to get the length of the LCS. (how to get those symbols?)
     */
    private static int findLcs(String s1, String s2, int x, int y) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (s1.charAt(x) == s2.charAt(y)) {
            return findLcs(s1, s2, x - 1, y - 1) + 1;
        }
        return Math.max(findLcs(s1, s2, x - 1, y), findLcs(s1, s2, x, y - 1));
    }

    private static int[][] findLcsBottomUp(String s1, String s2) {
        int[][] c = new int[s1.length() + 1][s2.length() + 1];
        int[][] d = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // Corner case: we shift the matrix one position, but not string
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    d[i][j] = 0;
                }
                else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i-1][j];
                    d[i][j] = 1;
                }
                else {
                    c[i][j] = c[i][j - 1];
                    d[i][j] = -1;
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        String s1 = "hello  world";
        String s2 = "dhdblowpdxlrd";
        System.out.println(findLcs(s1, s2, s1.length() - 1, s2.length() - 1));
        int[][] route = findLcsBottomUp(s1, s2);

        // printing the route
        Stack<Character> res = new Stack<>();
        print(s1, route, s1.length(), s2.length(), res);
        System.out.println(res);
    }

    private static void print(String s1, int[][] route, int x, int y, Stack<Character> res) {
        if (x <= 0 || y <=0) {
            return;
        }
        if (route[x][y] == 0) {
            print(s1, route, x - 1, y - 1, res);
            res.push(s1.charAt(x- 1));
        } else if (route[x][y] == 1) {
            print(s1, route, x - 1, y, res);
        } else {
            print(s1, route, x, y - 1, res);
        }
    }
}

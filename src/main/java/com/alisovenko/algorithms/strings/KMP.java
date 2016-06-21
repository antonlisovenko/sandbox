package com.alisovenko.algorithms.strings;

/**
 * @author alisovenko
 *         2/22/16.
 */
public class KMP {
    public int findSubstring(String str, String pattern) {
        int[][] dfa = createDfa(pattern);
        int n = str.length();
        int m = pattern.length();

        int i, p;
        for (i = 0, p = 0; i < n && p < m; i++) {
            p = dfa[str.charAt(i)][p];
        }
        return p == m ? i - m : n;
    }

    private int[][] createDfa(String pattern) {
        int m = pattern.length();
        int[][] dfa = new int[128][m];

        // matching state for 0
        dfa[pattern.charAt(0)][0] = 1;

        for (int X = 0, j = 1; j < m; j++) {
            // Copy mismatch cases
            for (int i = 0; i < 128; i++)
                dfa[i][j] = dfa[i][X];
            // Match case
            dfa[pattern.charAt(j)][j] = j + 1;
            X = dfa[pattern.charAt(j)][X];
        }
        return dfa;
    }

    public static void main(String[] args) {
        System.out.println(new KMP().findSubstring("We combed our catalog and found courses and Specializations that we think match your interests. Browse our recommendations below, and start learning something new today.", "dfdf"));

    }
}

package com.alisovenko.geeks4geeks;

/**
 * http://www.geeksforgeeks.org/longest-even-length-substring-sum-first-second-half/
 *
 * @author alisovenko 29.03.15
 */
public class LongestEvenLengthSubstring {
    public static int findLongestKSubstring(String s) {
        // dp[i][j] keeps the sum of numbers from i to j position
        int[][] dp = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = s.charAt(i) - '0';
        }

        int max = 0;
        for (int g = 2; g <= s.length(); g++) {
            for (int i = 0, j = i + g  - 1; j < s.length(); i++, j++) {
                int med = i + (j - i) / 2;

                dp[i][j] = dp[i][med] + dp[med + 1][j];

                if (g % 2 == 0 && dp[i][med] == dp[med + 1][j] && max < g) {
                    max = g;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(findLongestKSubstring("123123"));
        System.out.println(findLongestKSubstring("1538023"));
    }
}

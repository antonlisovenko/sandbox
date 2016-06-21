package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/29/16.
 */
public class OneEditDistanceIterative {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() - t.length() > 1 || t.length() - s.length() > 1) return false;

        return check(s, t, 0, 0, 0);
    }

    private boolean check(String s, String t, int i, int j, int dist) {
        if (dist > 1) return false;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++; j++;
            } else {
                return check(s, t, i + 1, j, dist + 1) ||
                        check(s, t, i, j + 1, dist + 1) ||
                        check(s, t, i + 1, j + 1, dist + 1);
            }
        }
        if (i != s.length() || j != t.length()) dist++;
        return dist == 1;
    }
}

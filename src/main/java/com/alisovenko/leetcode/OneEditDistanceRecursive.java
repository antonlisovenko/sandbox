package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/29/16.
 */
public class OneEditDistanceRecursive {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return false;
        if (s.length() - t.length() > 1 || t.length() - s.length() > 1) return false;

        return checkRecursively(s, t, 0, 0, 0);
    }

    private boolean checkRecursively(String s, String t, int i, int j, int dist) {
        if (dist > 1) return false;
        if (i == s.length() && j == t.length()) return dist == 1;
        if (i == s.length() || j == t.length()) return ++dist == 1;

        if (s.charAt(i) == t.charAt(j)) return checkRecursively(s, t, i + 1, j + 1, dist);
        return checkRecursively(s, t, i + 1, j, dist + 1) ||
                checkRecursively(s, t, i, j + 1, dist + 1) ||
                checkRecursively(s, t, i + 1, j + 1, dist + 1);
    }
}

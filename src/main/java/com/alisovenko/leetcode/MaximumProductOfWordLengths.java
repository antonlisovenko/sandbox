package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/3/16.
 */
public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        NavigableMap<Integer, List<String>> sortedBySize = new TreeMap<>(Comparator.reverseOrder());

        for (String s : words) {
            sortedBySize.putIfAbsent(s.length(), new ArrayList<>());
            sortedBySize.get(s.length()).add(s);
        }

        int w1 = 0, w2 = 0, max = 0;
        for (List<String> set : sortedBySize.values()) {
            for (String s : set) {
                int n = s.length();
                int[] arr = new int[256];
                for (char c : s.toCharArray()) arr[c]++;

                boolean hasCompared = false;
                for (List<String> set2 : sortedBySize.tailMap(n, true).values()) {
                    for (String s2 : set2) {
                        int m = s2.length();
                        if ((n < w1 && m <= w2) || (n <= w1 && m < w2)) break;
                        hasCompared = true;

                        if (noCommonLetters(s2, arr)) {
                            if (max < (m * n)) {
                                max = m * n;
                                w1 = n;
                                w2 = m;

                                if (m == n) return max;
                            }
                        }
                    }
                    if (!hasCompared) return max;
                }
            }
        }
        return max;
    }

    private boolean noCommonLetters(String s2, int[] chars) {
        for (char c : s2.toCharArray()) {
            if (chars[c] > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumProductOfWordLengths().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}));
    }
}

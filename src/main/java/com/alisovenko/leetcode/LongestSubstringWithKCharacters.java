package com.alisovenko.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * @author alisovenko
 *         7/3/16.
 */
public class LongestSubstringWithKCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        if (k >= s.length()) return s.length();

        int maxLength = Integer.MIN_VALUE;
        int lo = 0, hi = 0;
        Map<Character, Integer> histo = new HashMap<>();

        while (hi < s.length()) {
            if (histo.size() <= k) {
                maxLength = Math.max(maxLength, hi - lo);
                histo.merge(s.charAt(hi), 1, (o, n) -> ++o);
                hi++;
            } else {
                char c = s.charAt(lo);
                histo.compute(c, (p, v) -> --v);
                if (histo.get(c) == 0) histo.remove(c);
                lo++;
            }
        }

        // Check tail
        if (histo.size() <= k) maxLength = Math.max(maxLength, hi - lo);

        return maxLength;
    }

    public static void main(String[] args) {
        new LongestSubstringWithKCharacters().lengthOfLongestSubstringKDistinct("aaaaabbdef", 2);
    }
}

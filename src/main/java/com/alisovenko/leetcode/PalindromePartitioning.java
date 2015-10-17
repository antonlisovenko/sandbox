package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko 14.03.15
 */
public class PalindromePartitioning {
    public boolean[][] findAllPalindromes(String s) {
        if (s.length() == 1) {
            return new boolean[][]{{true, true}};
        }
        boolean[][] palindromes = new boolean[s.length()][s.length()];
        for (int idx = 0; idx < s.length(); idx++) {
            // Case 1: the character is at the center of palindrome
            for (int i = idx, p = idx; i >= 0 && p < s.length(); i--, p++) {
                if (s.charAt(i) != s.charAt(p))
                    break;
                palindromes[i][p] = true;
            }

            // Case 2: character is the same as its neighbour
            if (idx < s.length() - 1) {
                for (int i = idx, p = idx + 1; i >= 0 && p < s.length(); i--, p++) {
                    if (s.charAt(i) != s.charAt(p))
                        break;
                    palindromes[i][p] = true;
                }
            }
        }

        return palindromes;
    }

    public List<List<String>> partition(String s) {
        boolean[][] palindromes = findAllPalindromes(s);
        List<List<String>> result = new ArrayList<>();
        partition(s, 0, palindromes, new ArrayList<>(), result);
        return result;
    }

    public void partition(String s, int idx, boolean[][] p, List<String> current, List<List<String>> result) {
        if (idx == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if (p[idx][i]) {
                current.add(s.substring(idx, i + 1));
                partition(s, i + 1, p, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> result = new PalindromePartitioning().partition("seeslaveidemonstrateyetartsnomedievalsees");

        for (final List<String> strings : result) {
            System.out.println(strings);
        }
    }
}

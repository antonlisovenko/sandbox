package com.alisovenko.coderust.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Find all palindromes of the string
 *
 * @author alisovenko 06.12.14
 */
public class AllPalindromesInString {
    private static Set<String> allPalindromes(String str) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            result.addAll(findPalindromes(str, i));
        }
        return result;
    }

    private static List<String> findPalindromes(String str, int idx) {
        List<String> all = new ArrayList<>();

        // Case 1: the character is at the center of palindrome
        if (idx > 1 && idx < str.length() - 1) {
            for (int i = idx - 1, p = idx + 1; i >= 0 && p < str.length(); i--, p++) {
                if (str.charAt(i) != str.charAt(p))
                    break;
                all.add(str.substring(i, p+1));
            }
        }

        // Case 2: character is the same as its neighbour
        if (idx < str.length() -1) {
            for (int i = idx, p = idx + 1; i >= 0 && p < str.length(); i--, p++) {
                if (str.charAt(i) != str.charAt(p))
                    break;
                all.add(str.substring(i, p+1));
            }
        }
        return all;
    }

    public static void main(String[] args) {
        System.out.println(allPalindromes("aabbbaa"));
    }
}

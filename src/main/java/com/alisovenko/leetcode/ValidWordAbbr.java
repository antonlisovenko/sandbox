package com.alisovenko.leetcode;

import java.util.HashSet;

/**
 * @author alisovenko
 *         1/14/16.
 */
public class ValidWordAbbr {
    private HashSet<String> dict = new HashSet<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String s: dictionary) {
            dict.add(abbreviate(s));
        }
    }

    public boolean isUnique(String word) {
        return !dict.contains(abbreviate(word));
    }

    private String abbreviate(String s) {
        return s.length() > 2 ? String.valueOf(s.charAt(0)) + (s.length() - 2) + s.charAt(s.length() - 1) : s;
    }

    public static void main(String[] args) {
        System.out.println(new ValidWordAbbr(new String[]{"hello"}).isUnique("hello"));
    }
}

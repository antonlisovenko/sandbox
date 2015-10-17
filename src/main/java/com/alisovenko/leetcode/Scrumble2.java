package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko 31.03.15
 */
public class Scrumble2 {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return false;
        }
        if (s2 == null || s2.length() == 0) {
            return false;
        }

        return guess(s1, s2);
    }

    private boolean guess(String s1, String s2) {
        assert s1.length() == s2.length() : String.format("s1: %s, s2: %s", s1, s2);
        if (s1.length() == 0) {
            return false;
        }
        if (s1.length() == 1) {
            return s1.equals(s2);
        }
        if (s1.equals(s2)) {
            return true;
        }
        if (s2.length() % 2 == 0) {
            int partition = s1.length() / 2;
            String s11 = s1.substring(0, partition);
            String s12 = s1.substring(partition);

            String s21 = s2.substring(0, partition);
            String s22 = s2.substring(partition);

            return guess(s11, s21) && guess(s12, s22) || guess(s11, s22) && guess(s12, s21);
        }
        else {
            int partition = s1.length() / 2;
            String s11 = s1.substring(0, partition);
            String s12 = s1.substring(partition);

            String s21 = s2.substring(0, partition);
            String s22 = s2.substring(partition);

            String s23 = s2.substring(0, partition + 1);
            String s24 = s2.substring(partition + 1);

            return guess(s11, s21) && guess(s12, s22) || guess(s11, s24) && guess(s12, s23);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Scrumble2().isScramble("abb", "bab"));
        System.out.println(new Scrumble2().isScramble("aa", "ab"));
    }
}

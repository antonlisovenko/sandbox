package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Task: check that one word is the permutation of the other.
 *
 * Wrong:
 * 1. Permutation - full combination of the letters of the other
 * 2. Map - wrong, better use array!
 * @author alisovenko 10.08.14
 */
public class _1_3 {
    public static void main(String[] args) {
        assert isPermutation("hello", "hell");
        assert isPermutation("hello", "helo");
        assert isPermutation("hello", "e");
        assert !isPermutation("hello", "helll");
        assert !isPermutation("hello", "helloo");

        System.out.println("Everything is ok");
    }

    public static boolean isPermutation(String sample, String target) {
        Map<Character, Short> freqS = frequency(sample);
        for (final char c : target.toCharArray()) {
            if (!freqS.containsKey(c) || freqS.merge(c, (short)0, (o, n) -> (short) (o - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Short> frequency(String sample) {
        Map<Character, Short> result = new HashMap<>();
        for (final char c : sample.toCharArray()) {
            result.merge(c, (short)1, (o, n) -> (short)(o + n));
        }
        return result;
    }
}

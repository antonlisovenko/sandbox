package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko 16.10.14
 */
public class AllPermutationsOfString {
    public static void find (String prefix, String suffix, List<String> result) {
        if (suffix.length() == 0) {
            result.add(prefix);
        }
        for (int i = 0; i < suffix.toCharArray().length; i++) {
            find(prefix + suffix.toCharArray()[i], suffix.substring(0, i) + suffix.substring(i + 1), result);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        find("", "abcdt", result);
        System.out.println(result.size() + ": " +result);
    }
}

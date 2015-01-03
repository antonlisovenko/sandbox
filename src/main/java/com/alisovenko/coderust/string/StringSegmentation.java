package com.alisovenko.coderust.string;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alisovenko 13.12.14
 */
public class StringSegmentation {
    private static boolean isSegmented(String S, Set<String> dict) {
        // 0 - not cached, -1 - absent, 1 - found in dictionary
        int[] cache = new int[S.length() +1];
        cache[0] = 1;

        for (int i = 0; i <= S.length(); i++) {
            for (int p = 0; p < i; p++) {
                if (cache[p] > 0 && dict.contains(S.substring(p, i))) {
                    cache[i] = 1;
                    break;
                }
            }
            if (cache[i] != 1) {
                cache[i] = -1;
            }
        }

        return cache[S.length()] > 0;
    }

    public static void main(String[] args) {
        Set<String> dict =new HashSet<>(Arrays.asList("apple", "pear", "pier", "pie"));
        System.out.println(isSegmented("applepie", dict));
        System.out.println(isSegmented("applepeer", dict));
    }
}

package com.alisovenko.leetcode;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author alisovenko
 *         1/11/16.
 */
public class WordBreak2 {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 ||!breakExists(s, wordDict)) return Collections.emptyList();

        List<String> result = new ArrayList<>();
        recurseAndFind(s, 0, wordDict, result, new ArrayList<>());
        return result;
    }

    private void recurseAndFind(String s, int idx, Set<String> dict, List<String> result, List<String> prefix) {
        // Base case - found the new phrase
        if (idx >= s.length()) {
            result.add(prefix.stream().collect(Collectors.joining(" ")));
            return;
        }
        for (int i = idx; i < s.length();i++) {
            String word = s.substring(idx, i);
            if (dict.contains(word)) {
                prefix.add(word);
                recurseAndFind(s, i, dict, result, prefix);
                prefix.remove(prefix.size() - 1);
            }
        }
    }

    private boolean breakExists (String s, Set<String> wordDict) {
        boolean[] cache = new boolean[s.length() + 1];
        cache[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int p = 0; p < i; p++) {
                if (cache[p] && wordDict.contains(s.substring(p, i))) {
                    cache[i] = true;
                }
            }
        }

        return cache[cache.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak2().wordBreak("catsanddog", Sets.newHashSet("cat","cats","and","sand","dog")));
    }
}

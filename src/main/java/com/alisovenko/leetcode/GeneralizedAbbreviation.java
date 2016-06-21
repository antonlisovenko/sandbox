package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko
 *         2/7/16.
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        if (word.length() == 0) return Collections.singletonList(word);
        List<String> result = new ArrayList<>();

        generate(word, 0, new StringBuilder(), result);

        return result;
    }

    private void generate(String word, int i, StringBuilder builder, List<String> result) {
        if (i >= word.length()) {
            if (builder.length() > 0) result.add(builder.toString());
            return;
        }

        for (int gap = 0; gap <= word.length() - i; gap++) {
            String replacement;
            if (gap == 0) {
                replacement = String.valueOf(word.charAt(i));
            } else {
                replacement = String.valueOf(gap);
                if (i + gap < word.length()) replacement += word.substring(i + gap, i + gap + 1);
            }
            builder.append(replacement);

            generate(word, i + gap + 1, builder, result);

            builder.delete(builder.length() - replacement.length(), builder.length());
        }
    }
}

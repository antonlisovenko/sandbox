package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 06.04.15
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int L) {
        if (words.length == 0 || L == 0) {
            return Arrays.asList("");
        }
        int start = 0; // index of the word to start new line from
        int length = 0; // number of characters in current line
        int numberOfWords = 0;
        List<String> result = new ArrayList<>();

        while (start < words.length) {
            int i;
            for (i = start; i < words.length; i++) {
                if (length + numberOfWords + words[i].length() > L) {
                    break;
                }
                numberOfWords++;
                length += words[i].length();
            }

            if (i < words.length) {
                result.add(newLine(words, start, start + numberOfWords, length, L));
            }
            else {
                String s = "";
                for (int p = start; p < words.length; p++) {
                    s += words[p] + ' ';
                }
                s = s.substring(0, s.length() - 1);
                if (s.length() < L) {
                    int l = s.length();
                    for (int p = 0; p < L - l; p++) {
                        s += " ";
                    }
                }
                result.add(s);

            }
            start += numberOfWords; // for next iteration
            length = 0;
            numberOfWords = 0;
        }
        return result;
    }

    /**
     *
     * @param words
     * @param start inclusively
     * @param end exclusively
     * @param wordsLength
     * @param L
     * @return
     */
    private String newLine(String[] words, int start, int end, int wordsLength, int L) {
        StringBuilder sb = new StringBuilder();

        int numberOfSpaces = end - start - 1;
        int totalSpaces = (L - (wordsLength + numberOfSpaces)) + numberOfSpaces;
        int spaceLength = numberOfSpaces == 0 ? L - wordsLength : totalSpaces / numberOfSpaces;
        int tail = numberOfSpaces == 0 ? 0 : totalSpaces % numberOfSpaces;

        for (int i = start; i < end; i++) {
            sb.append(words[i]);

            if (i == end - 1 && numberOfSpaces > 0) {
                break;
            }
            for (int p = 0; p < spaceLength; p++) {
                sb.append(' ');
            }
            if (tail-- > 0) {
                sb.append(' ');
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(new TextJustification().fullJustify(new String[]{"Here","is","an","example","of","text","justification."}, 14));
    }
}

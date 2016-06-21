package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alisovenko
 *         2/13/16.
 */
public class EncodeDecodeStrings {
    private static String DELIM = "&|@";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) return "";
        StringBuilder sb = new StringBuilder(DELIM);

        for (String s: strs) {
            sb.append(s == null ? "" : s).append(DELIM);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s == null || s.length() == 0) return Collections.emptyList();

        s = s.substring(DELIM.length());

        List<String> tokens = new ArrayList<>();

        if (!s.contains(DELIM)) {
            tokens.add(s);
        } else {
            int idx;
            String t = s;
            while((idx = t.indexOf(DELIM)) >= 0) {
                tokens.add(t.substring(0, idx));
                t = t.substring(idx + DELIM.length());
            }
        }

        return tokens;
    }

    public static void main(String[] args) {
        EncodeDecodeStrings encode = new EncodeDecodeStrings();
        System.out.println(encode.decode(encode.encode(Arrays.asList("", ""))));
        System.out.println(encode.decode(encode.encode(Arrays.asList("s", "g"))));
        System.out.println(encode.decode(encode.encode(Arrays.asList("@"))));
    }
}

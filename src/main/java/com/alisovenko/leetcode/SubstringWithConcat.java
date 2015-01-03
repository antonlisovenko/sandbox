package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko 09.12.14
 */
public class SubstringWithConcat {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<>();

        Map<String, Integer> lMap = new HashMap<>();
        for (String s : L) {
            if (lMap.containsKey(s)) {
                lMap.put(s, lMap.get(s) + 1);
            }
            else {
                lMap.put(s, 1);
            }
        }
        int N = L.length;
        int M = L[0].length();

        int i = 0;

        while (i <= (S.length() - N * M)) {
            Map<String, Integer> rMap = new HashMap<>();
            int p = 0;
            for (; p < N; p++) {
                int start = i + p * M;
                String s = S.substring(start, start + M);
                if (rMap.containsKey(s)) {
                    rMap.put(s, rMap.get(s) + 1);
                }
                else {
                    rMap.put(s, 1);
                }
                if (!lMap.containsKey(s) || rMap.get(s) > lMap.get(s)) {
                    p = 0;
                    break;
                }
            }
            if (p == N) {
                result.add(i);
            }
            i++;
        }
        return result;

    }
    public static void main(String[] args) {
//        System.out.println(new SubstringWithConcat().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(new SubstringWithConcat().findSubstring("a", new String[]{"a"}));
    }
}

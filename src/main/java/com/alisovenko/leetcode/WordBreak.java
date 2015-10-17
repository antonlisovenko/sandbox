package com.alisovenko.leetcode;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author alisovenko 21.01.15
 */
public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] c = new boolean[s.length() + 1];
        char[] ch = s.toCharArray();

        for (int i = 1; i <= ch.length; i++) {
            for (int p = 0; p < i; p++) {
//                System.out.printf("i: %d, p: %d, substr: %s\n", i, p, substring);
                if ((p == 0 || c[p - 1]) && dict.contains(s.substring(p, i))) {
//                    System.out.printf("c[%d]: true\n", i - 1);
                    c[i - 1] = true;
                    break;
                }
            }
        }
        return c[s.length() - 1];
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("leetcode", Sets.newHashSet("leet", "code")));
        System.out.println(wb.wordBreak("programcreek", Sets.newHashSet("programcree","program","creek")));
        System.out.println(wb.wordBreak("programcreek", Sets.newHashSet("programcr","progra","creek")));
        System.out.println(wb.wordBreak("cars", Sets.newHashSet("car","ca","rs")));
    }
}

package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/12/16.
 */
public class WildcardMatching3 {
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }

        return j == p.length();
    }

    public static void main(String[] args) {
//        at(new WildcardMatching3().isMatch("ssss", "***"));
//        at(new WildcardMatching3().isMatch("ssss", "*"));
//        at(new WildcardMatching3().isMatch("s", "****"));
//        at(new WildcardMatching3().isMatch("svp r", "*r*"));
//        at(new WildcardMatching3().isMatch("dfoke", "*d*f*o*k*e*"));
        af(new WildcardMatching3().isMatch("aababaaaaba", "a**abb*"));
//        at(new WildcardMatching3().isMatch("aaaabbbbaaabb", "a**bb**aaa****b"));
//        at(new WildcardMatching3().isMatch("abbbbbbbabbbbbbabbbba", "a****a****a****a"));
//        at(new WildcardMatching3().isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
//                "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
//        af(new WildcardMatching3().isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
//                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
//
//
//        af(new WildcardMatching3().isMatch("aaaabbbbaaabb", "a****b****a****c"));
//        af(new WildcardMatching3().isMatch("aaaabbbbaaabb", "a****b****a****a"));
//        af(new WildcardMatching3().isMatch("abbbbbbbabbbbbbabbbb", "a****a****a****a"));
    }

    private static void af(boolean match) {
        assert !match;
    }

    private static void at(boolean ssss) {
        assert ssss;
    }
}

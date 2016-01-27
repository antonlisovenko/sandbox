package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/12/16.
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        p = preprocess(p);
        return isMatch(s, 0, p, 0);
    }

    private String preprocess(String p) {
        StringBuilder sb = new StringBuilder();
        boolean foundAsterix = false;

        for (char c : p.toCharArray()) {
            if (c != '*') {
                sb.append(c);
                foundAsterix = false;
            }
            else if (!foundAsterix) {
                sb.append('*');
                foundAsterix = true;
            }
        }
        return sb.toString();
    }


    // s.length() && p.length() -> true
    // s.length() && !p.length() -> true if left characters are ? and *
    // !s.length() && p.length() -> false

    private boolean isMatch(String s, int sIdx, String p, int pIdx) {
        // Base case 1: we came to end in both patterns
        if (sIdx == s.length() && pIdx == p.length()) return true;

        // Base case 2: we came to end of string, but not to the end of p
        if (sIdx == s.length() && p.charAt(pIdx) != '*') return false;

        // Checking for '*' sign
        if (safeCompare(p, pIdx, '*')) return (sIdx < s.length() && isMatch(s, sIdx + 1, p, pIdx)) || isMatch(s, sIdx, p, pIdx + 1);

        // Checking for '?' sign
        if (safeCompare(p, pIdx, '?') || safeCompare(p, pIdx, s, sIdx)) return isMatch(s, sIdx + 1, p, pIdx + 1);

        return false;
    }

    private boolean safeCompare(String s, int idx, String s2, int s2idx) {
        return idx < s.length() && s2idx < s2.length() && s.charAt(idx) == s2.charAt(s2idx);
    }
    private boolean safeCompare(String s, int idx, char c) {
        return idx < s.length() && s.charAt(idx) == c;
    }

    public static void main(String[] args) {
        at(new WildcardMatching().isMatch("ssss", "***"));
        at(new WildcardMatching().isMatch("ssss", "*"));
        at(new WildcardMatching().isMatch("s", "****"));
        at(new WildcardMatching().isMatch("svp r", "*r*"));
        at(new WildcardMatching().isMatch("dfoke", "*d*f*o*k*e*"));
        at(new WildcardMatching().isMatch("aaaabbbbaaabb", "a****b****a****b"));
        at(new WildcardMatching().isMatch("abbbbbbbabbbbbbabbbba", "a****a****a****a"));
        at(new WildcardMatching().isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
                "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
        at(new WildcardMatching().isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));


        af(new WildcardMatching().isMatch("aaaabbbbaaabb", "a****b****a****c"));
        af(new WildcardMatching().isMatch("aaaabbbbaaabb", "a****b****a****a"));
        af(new WildcardMatching().isMatch("abbbbbbbabbbbbbabbbb", "a****a****a****a"));
    }

    private static void af(boolean match) {
        assert !match;
    }

    private static void at(boolean ssss) {
        assert ssss;
    }
}

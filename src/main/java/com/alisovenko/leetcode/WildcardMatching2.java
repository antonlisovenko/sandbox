package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/12/16.
 */
public class WildcardMatching2 {
    public boolean isMatch(String s, String p) {
        int ss = 0, pp = 0;

        boolean everywhere = false;
        label:
        while (true) {
            if (ss == s.length()) {
                if (pp == p.length()) return true;
                if (p.charAt(pp) == '*') {
                    pp++;
                    continue;
                } else {
                    return false;
                }
            }
            if (pp == p.length() - 1 && p.charAt(pp) == '*') return true; // on the last pattern symbol we allow '*' to match the rest of s
            if (pp == p.length()) return false; // reached end of pattern but not the end of string
            if (p.charAt(pp) == '*') {
                everywhere = true;
                pp++;
                continue;
            }
            // Ok, we are inside string and pattern (no '*'), so can find the next string matching
            // This is direct matching (no wildcards until this time)
            if (!everywhere) {
                if (!matches(s, p, ss, pp)) return false;
                pp++;
                ss++;
            } else {
                // This is the substring after wildcard - iterating the string until we find matching of substrings
                for (; ss < s.length(); ss++) {
                    for (int t = ss, l = pp; ; t++, l++) {
                        // Special case: we came to the end of pattern, but not to the end of string ("abb", "*b") - need to check that two strings end equaly
                        if (l == p.length() && t != s.length()) {
                            return checkEqualEnding(s, p);
                        }
                        if (t == s.length() || l == p.length() || p.charAt(l) == '*') {
                            ss = t;
                            pp = l;
                            continue label;
                        }
                        if (!matches(s, p, t, l)) break;
                    }
                }
            }
        }
    }

    private boolean checkEqualEnding(String s, String p) {
        int ss = s.length() - 1, pp = p.length() - 1;

        while (ss >= 0 && pp >= 0 && p.charAt(pp) != '*' && matches(s, p, ss, pp)) {
            ss--;
            pp--;
        }
        return (pp == 0 && ss == 0) || p.charAt(pp) == '*';
    }

    private boolean matches(String s, String p, int ss, int pp) {
        return p.charAt(pp) == '?' || s.charAt(ss) == p.charAt(pp);
    }

    public static void main(String[] args) {
        at(new WildcardMatching2().isMatch("ssss", "***"));
        at(new WildcardMatching2().isMatch("ssss", "*"));
        at(new WildcardMatching2().isMatch("s", "****"));
        at(new WildcardMatching2().isMatch("svp r", "*r*"));
        at(new WildcardMatching2().isMatch("dfoke", "*d*f*o*k*e*"));
        at(new WildcardMatching2().isMatch("aaaabbbbaaabb", "a****b****a****b"));
        at(new WildcardMatching2().isMatch("abbbbbbbabbbbbbabbbba", "a****a****a****a"));
        at(new WildcardMatching2().isMatch("aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
                "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"));
        af(new WildcardMatching2().isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));


        af(new WildcardMatching2().isMatch("aaaabbbbaaabb", "a****b****a****c"));
        af(new WildcardMatching2().isMatch("aaaabbbbaaabb", "a****b****a****a"));
        af(new WildcardMatching2().isMatch("abbbbbbbabbbbbbabbbb", "a****a****a****a"));
    }

    private static void af(boolean match) {
        assert !match;
    }

    private static void at(boolean ssss) {
        assert ssss;
    }
}

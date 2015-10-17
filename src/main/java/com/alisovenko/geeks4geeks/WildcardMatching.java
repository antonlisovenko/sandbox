package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 14.01.15
 */
public class WildcardMatching {
    public static boolean matches(String pattern, String str) {
        // base case 1: we came to end in both string and pattern -> matched
        if (str.length() == 0 && pattern.length() == 0) {
            return true;
        }
        // base case 2: we came to end in string, but pattern contains more symbols (and it is not '*')
        if (str.length() == 0 && !pattern.equals("*")) {
            return false;
        }
        // checking the next symbol matching
        if (charsMatch(pattern, '?') || charsMatch(pattern, str)) {
            return matches(pattern.substring(1), str.substring(1));
        }
        // checking wildcard - there are two scenarios: either we skip the wildcard, or we skip the next symbol
        if (charsMatch(pattern, '*')) {
            return matches(pattern.substring(1), str) || matches(pattern, str.substring(1));
        }
        return false;
    }

    private static boolean charsMatch(String s, char ch) {
        return s.length() > 0 && s.charAt(0) == ch;
    }
    private static boolean charsMatch(String s, String p) {
        return s.length() > 0 && p.length() > 0 && s.charAt(0) == p.charAt(0);
    }

    public static void main(String[] args) {
        eq("g*ks", "geeks"); // Yes
        eq("ge?ks*", "geeksforgeeks"); // Yes
        neq("g*k", "gee");  // No because 'k' is not in second
        neq("*pqrs", "pqrst"); // No because 't' is not in first
        eq("abc*bcd", "abcdhghgbcd"); // Yes
        neq("abc*c?d", "abcd"); // No because second must have 2 instances of 'c'
        eq("*c*d", "abcd"); // Yes
        eq("*?c*d", "abcd"); // Yes
    }

    private static void eq(String s, String s2) {
        assert matches(s, s2);
    }
    private static void neq(String s, String s2) {
        assert !matches(s, s2);
    }
}

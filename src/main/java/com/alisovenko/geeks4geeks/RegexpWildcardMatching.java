package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 14.01.15
 */
public class RegexpWildcardMatching {
    public static boolean matches(String pattern, String str, int p, int s) {
        // base case 1: we came to end in both string and pattern -> matched
        if (str.length() == s && pattern.length() == p) {
            return true;
        }
        // base case 3: we came to end in string, but pattern contains more symbols (and it is not '*')
        if (str.length() == s && !endsWithApostroph(pattern, p)) {
            return false;
        }
        // checking the symbol matching (in case the next character is not *)
        if (charsEqual(pattern, p, '?') || (charsEqual(pattern, p, str, s) && !isApostroph(pattern, p + 1))) {
            return matches(pattern, str, ++p, ++s);
        }
        // checking wildcard (if it is the next character)
        if (isApostroph(pattern, p + 1)) {
            // we either skip both character and wildcard
            if (matches(pattern, str, p + 2, s)) {
                return true;
            }
            //or check that the character is equal to the string one - then recurse
            if (charsEqual(str, s, pattern.charAt(p))) {
                return matches(pattern, str, p, ++s);
            }
        }
        return false;
    }

    private static boolean isApostroph(String pattern, int p) {
        return pattern.length() > p && pattern.charAt(p) == '*';
    }


    private static boolean charsEqual(String str, int p, char c) {
        return str.length() > p && str.charAt(p) == c;
    }
    private static boolean charsEqual(String str, int p, String str2, int c) {
        return str.length() > p && str2.length() > c && str.charAt(p) == str2.charAt(c);
    }

    private static boolean endsWithApostroph(String pattern, int p) {
        return (p == pattern.length() - 1 && pattern.charAt(p) == '*'
        || p == pattern.length() - 2 && pattern.charAt(p + 1) == '*');
    }

    public static void main(String[] args) {
        neq("g*ks", "geeks"); // No
        eq("ge?ks*", "geeksss"); // Yes
        eq("ge?ks*", "geek"); // Yes
        eq("ge?ks*", "geeks"); // Yes
        neq("g*ks*", "geeks"); // No
        eq("g*ks*", "ggggks"); // Yes
        eq("g*ks*", "ggggk"); // Yes
        neq("g*k", "gee");  // No because 'k' is not in second
        neq("*pqrs", "pqrst"); // No because 't' is not in first

    }

    private static void eq(String s, String s2) {
        assert matches(s, s2, 0, 0);
    }

    private static void neq(String s, String s2) {
        assert !matches(s, s2, 0, 0);
    }
}

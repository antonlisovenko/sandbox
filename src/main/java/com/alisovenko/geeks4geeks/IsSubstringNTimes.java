package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 13.01.15
 */
public class IsSubstringNTimes {
    public static boolean isSubstringNTimes(String str) {
        char[] chars = str.toCharArray();
        int N = chars.length;

        // check case 1: there are even number of substrings: thus we iterate from the middle and check that left and right parts are equal
        if ((chars.length & 1) == 0) {
            if (str.substring(0, N / 2).equals(str.substring(N / 2, N))) {
                return true;
            }
        }

        // check case 2: there are odd number of substrings: moving from head and comparing the backtracked word with tail - if found the match - trying to find this substring further
        String candidate;

        for (int i = 0; i < chars.length / 2; i++) {
            int lo = i, hi = chars.length - 1;

            while (chars[lo--] == chars[hi--]) {
                if (lo == 0) {
                    // Ok we found the matching substring, let's test it
                    candidate = str.substring(0, i + 1);

                    if (testCandidate(candidate, str)) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    private static boolean testCandidate(String candidate, String str) {
        int N = str.length();
        int M = candidate.length();

        if (N % M != 0) {
            return false;
        }

        for (int i = 0; i < N - M * 2 + 1; i += M * 2) {
            // check two substrings with each other
            for (int p = i, t = i + M; p < M; p++, t++) {
                if (str.charAt(p) != str.charAt(t)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSubstringNTimes("abcabcabc"));
        System.out.println(isSubstringNTimes("abadabad"));
        System.out.println(isSubstringNTimes("aabaabaabaab"));
        System.out.println(isSubstringNTimes("abcdabc"));
        System.out.println(isSubstringNTimes("GEEKSFORGEEKS"));
        System.out.println(isSubstringNTimes("GEEKGEEK"));
        System.out.println(isSubstringNTimes("AAAACAAAAC"));
    }
}

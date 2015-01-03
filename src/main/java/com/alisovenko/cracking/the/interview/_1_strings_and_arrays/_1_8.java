package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

/**
 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s i and
 * s2, write code to check if s2 is a rotation of si using only one call to isSubstring (e.g.,"waterbottle"is a rota-
 * tion of"erbottlewat").
 *
 * Resolution: complete bullshit. Simpty concatenate s1+s1 and find isSubstring(s1+s1, s2)
 * @author alisovenko 16.08.14
 */
public class _1_8 {
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isRotation(s1, s2));
    }

    public static boolean isRotation(String str, String str2) {
        int stopIdx = -1;

        if (str.equals(str2)) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            int p;
            for (p = 0; p < str2.length(); p++) {
                if (str.length() > i + p && str.charAt(i + p) != str2.charAt(p)) {
                    break;
                }
                if (p == str2.length()) {
                    return false;
                }
            }
            stopIdx = p;
        }
        if (stopIdx == 0) {
            return false;
        }

        return str.contains(str2.substring(stopIdx, str2.length()));
    }
}

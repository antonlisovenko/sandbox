package com.alisovenko.arithmetics;

/**
 * @author alisovenko 31.01.15
 */
public class ConvertStringToInt {
    private static int convert(String s) {
        int result = 0;
        for (char c : s.toCharArray()) {
            result = result * 10 + (c - '0');
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convert("34324"));
    }
}

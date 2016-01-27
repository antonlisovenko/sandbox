package com.alisovenko.algorithms.strings;

/**
 * @author alisovenko
 *         12/17/15.
 */
public class CodePointCheck {
    public static void main(String[] args) {
        "Hello".codePoints().forEach(System.out::print);
        System.out.println();
        "Hello".chars().forEach(System.out::print);

        System.out.println();

        System.out.println(Character.charCount(0x00DF));
        System.out.println(Character.charCount(0x6771));
        System.out.println(Character.charCount(0x10400));
    }
}

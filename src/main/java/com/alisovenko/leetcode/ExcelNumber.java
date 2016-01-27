package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         12/17/15.
 */
public class ExcelNumber {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException();
        }
        int f = 0, m = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            f += num(s.charAt(i)) * m;
            m *= 26;
        }
        return f;
    }
    private int num(char c) {
        return c - 'A' + 1;
    }

    public static void main(String[] args) {
        System.out.println(new ExcelNumber().titleToNumber("AA"));
        System.out.println(new ExcelNumber().titleToNumber("A"));
        System.out.println(new ExcelNumber().titleToNumber("AF"));
    }
}

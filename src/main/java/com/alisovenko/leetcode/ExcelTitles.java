package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         12/17/15.
 */
public class ExcelTitles {
    public String convertToTitle(int n) {
        if (n < 1) return "";
        StringBuilder b = new StringBuilder();

        int r = n;

        while (r > 0) {
            r--;
            b.append(byCode(r % 26));
            r /= 26;
        }

        return b.reverse().toString();
    }

    private char byCode(int p) {
        assert p >= 0 && p < 26;

        return (char)('A' + p);
    }

    public static void main(String[] args) {
        System.out.println(new ExcelTitles().convertToTitle(1));
        System.out.println(new ExcelTitles().convertToTitle(27));
        System.out.println(new ExcelTitles().convertToTitle(28));
    }
}

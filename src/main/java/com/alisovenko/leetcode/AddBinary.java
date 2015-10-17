package com.alisovenko.leetcode;

/**
 * @author alisovenko 08.03.15
 */
public class AddBinary {
    private static final int OFFSET = 3 * '0';

    private char performSum(StringBuilder result, char x, char y, char borrowed) {
        int z = (int)x + (int)y + borrowed - OFFSET;
        if (z == 1 || z == 3) result.append('1');
        if (z == 0 || z == 2) result.append('0');

        return z > 1 ? '1' : '0';
    }

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
        StringBuilder result = new StringBuilder();

        char borrowed = '0';
        for(int i = aa.length - 1, p = bb.length - 1; i >= 0 || p >= 0; i--, p--) {
            char x = i < 0 ? '0' : aa[i];
            char y = p < 0 ? '0' : bb[p];
            borrowed = performSum(result, x, y, borrowed);
        }

        // overriding the extra bit it necessary
        if (borrowed == '1') result.append("1");

        return result.reverse().toString();
    }


    public static void main(String[] args) {
//        System.out.println(new AddBinary().addBinary("0", "0"));
//        System.out.println(new AddBinary().addBinary("1", "1"));
        System.out.println(new AddBinary().addBinary("101001",
                "11001"));
    }
}

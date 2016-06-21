package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         2/12/16.
 */
public class ExpressionAddOperation {
    static int pos = 0;

    public List<String> addOperators(String num, int target) {
        List<String> allFormulas = new ArrayList<>();
        findAllFormulas(num, 0, new StringBuilder(), allFormulas);

        List<String> result = new ArrayList<>();
        for (String f: allFormulas) {
            pos = 0;
            if (calculate(f) == target) result.add(f);
        }
        return result;
    }

    void findAllFormulas(String num, int p, StringBuilder builder, List<String> result) {
        if (p == num.length()) {
            result.add(builder.toString());
            return;
        }
        // window
        for (int i = p + 1; i <= num.length(); i++) {
            String substr = num.substring(p, i);
            if (substr.startsWith("0") && substr.length() > 1) continue;

            builder.append(substr);
            if (i == num.length()) {
                if (p != 0) findAllFormulas(num, i, builder, result);
            } else {
                for (char s: new char[] {'*', '+', '-'}) {
                    builder.append(s);
                    findAllFormulas(num, i, builder, result);
                    builder.deleteCharAt(builder.length() - 1);
                }
            }

            builder.delete(builder.length() - i + p, builder.length());
        }
    }

    int calculate(String f) {
        int term = term(f);

        while (nextChar(f, '+') || nextChar(f, '-')) {
            char s = nextChar(f);
            int other = term(f);
            if (s == '+') term += other;
            else term -= other;
        }
        return term;
    }
    int term(String s) {
        int c = num(s);

        while (nextChar(s, '*')) {
            pos++;
            c *= num(s);
        }
        return c;
    }
    int num(String s) {
        int start = pos;
        while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
            pos++;
        }
        return Integer.parseInt(s.substring(start, pos));
    }

    boolean nextChar(String s, char ch) {
        return pos < s.length() && s.charAt(pos) == ch;
    }
    char nextChar(String s) {
        return s.charAt(pos++);
    }

    public static void main(String[] args) {
        System.out.println(new ExpressionAddOperation().addOperators("123", 6));
        System.out.println(new ExpressionAddOperation().addOperators("232", 8));
        System.out.println(new ExpressionAddOperation().addOperators("105", 5));
        System.out.println(new ExpressionAddOperation().addOperators("00", 0));
        System.out.println(new ExpressionAddOperation().addOperators("3456237490", 342343));
    }
}

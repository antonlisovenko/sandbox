package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/20/16.
 */
public class BasicCalculator {
    /*
        expr := term (+/- term)...
        term := num | '(' + expr + ')'
        num  := [0-9]+
    */
    private int i = 0; // pointer on s
    private String s; // string
    public int calculate(String s) {
        this.s = s;
        return expr();
    }

    private int expr() {
        int term = term();

        while (nextChar() == '+' || nextChar() == '-') {
            char c = nextChar();
            i++;
            if (c == '+') term += term();
            else          term -= term();
        }
        return term;
    }

    private int term() {
        if (nextChar() == '(') {
            int res = expr();
            if (nextChar() != ')') throw new IllegalArgumentException();

            nextChar();
            return res;
        }
        return num();
    }
    private void skipBlanks() {
        while (i < s.length() && Character.isWhitespace(s.charAt(i)))            i++;
    }
    private char nextChar() {
        skipBlanks();
        return i >= s.length() ? '#' : s.charAt(i);
    }
    private int num() {
        skipBlanks();
        if (i >= s.length()) throw new IllegalArgumentException();

        int pos = i;
        while (i < s.length() && Character.isDigit(s.charAt(i))) i++;
        if (pos == i) throw new IllegalArgumentException();

        return Integer.parseInt(s.substring(pos, i));
    }

    public static void main(String[] args) {
        new BasicCalculator().calculate("1+1");
    }
}

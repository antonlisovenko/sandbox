package com.alisovenko.geeks4geeks;

/**
 * http://www.geeksforgeeks.org/forums/topic/facebook-interview-3/
 * <p>
 * BNF:
 * <formula> ::= <expr> "=" <expr>
 * <expr> ::= ["-"] <term> ["+"|"-"<term>]...;
 * <term> ::= "(" <expr> ")" | <var>;
 * <var> ::= \d+|(\d* "x")
 *
 * @author alisovenko 07.04.15
 */
public class ParseFormulaWithBrackets2 {
    private static int idx;
    private static String s;

    public static double calculate(String str) {
        // reseting idx and str
        idx = 0;
        s = str;

        Tuple t = parseFormula();

        System.out.printf("%dx = %d\n", t.x, t.v);
        return t.x == 0 ? 0 : (double) t.v / t.x;
    }

    private static Tuple parseFormula() {
        Tuple t = parseExpression();

        skipBlanks();
        if (nextChar() != '=') {
            throw new IllegalArgumentException("Wrong formula, found '" + nextChar() + "' instead of '='");
        }

        skipChar();
        Tuple t2 = parseExpression();

        return new Tuple(t.x - t2.x, t2.v - t.v);
    }

    private static Tuple parseExpression() {
        skipBlanks();

        boolean negative = false;
        if (nextChar() == '-') {
            negative = true;
            skipChar();
            skipBlanks();
        }

        Tuple t = parseTerm();
        t = negative ? t.negate() : t;

        skipBlanks();

        while (nextChar() == '-' || nextChar() == '+') {
            char sign = nextChar();
            skipChar();
            Tuple t2 = parseTerm();

            if (sign == '-') {
                t = t.add(t2.negate());
            } else {
                t = t.add(t2);
            }
            skipBlanks();
        }
        return t;
    }

    private static Tuple parseTerm() {
        skipBlanks();

        Tuple t;
        if (nextChar() == '(') {
            skipChar();
            t = parseExpression();

            if (nextChar() != ')') {
                throw new IllegalArgumentException("Formula is incorrect, expected ')', but found " + nextChar());
            }
            skipChar();

            return t;
        }
        return parseVar();
    }

    private static Tuple parseVar() {
        int res = 0;
        if (idx == s.length() || (nextChar() != 'x' && (nextChar() < '0' || nextChar() > '9'))) {
            throw new IllegalArgumentException("Formula is incorrect, expected either constant or variable, but found " + nextChar());
        }
        if (nextChar() >= '0' && nextChar() <= '9') {
            while (nextChar() >= '0' && nextChar() <= '9') {
                res += res * 10 + nextChar() - '0';
                skipChar();
            }
        }
        if (nextChar() == 'x') {
            skipChar();
            return new Tuple(res, 0);
        }
        return new Tuple(0, res);
    }

    private static void skipBlanks() {
        while (idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }
    }

    private static char nextChar() {
        if (idx >= s.length()) {
            return 0;
        }
        return s.charAt(idx);
    }

    private static void skipChar() {
        idx++;
    }

    private static class Tuple {
        int x;
        int v;

        public Tuple(int x, int v) {
            this.x = x;
            this.v = v;
        }

        public Tuple add(Tuple t) {
            this.x += t.x;
            this.v += t.v;
            return this;
        }

        public Tuple negate() {
            this.x = (int) -((long) x);
            this.v = (int) -((long) v);
            return this;
        }

        @Override
        public String toString() {
            return x + "," + v;
        }
    }

    public static void main(String[] args) {
        check("5x + (4 - 3 - (2x - 5) -4x) = -(3 + 2) + 2x", (double)11/3);
        check("5x-((4x  -7x) -( 3-1)) = 0", (double) -2 / 8);
        check("5x-((2x + 5)) = 0", (double) 5 / 3);
        check("-5x-((2x + 5)) = 0", (double) 5 / -7);
        check("x = 2x", 0);
        fails("5x-((2x + 5) = 0");
        fails("xx");
        fails("5-2-6");
        fails("(3(5 + 4x)) = 4x");
        fails("(3x +-(5 + 4x)) = 4x");
    }

    private static void fails(String s) {
        try {
            calculate(s);
            throw new IllegalStateException();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }
    }


    private static void check(String s, double v) {
        double res = calculate(s);
        assert res == v;
        System.out.println("OK: " + s + " -> " + res);
    }

}

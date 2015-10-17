package com.alisovenko.geeks4geeks;

/**
 * http://www.geeksforgeeks.org/forums/topic/facebook-interview-3/
 * <p>
 * BNF:
 * <calculate := formula=formula
 * formula := (bracketed)+;
 * bracketed := '(' bracketed ')' | (expr)+
 * expr := [+/-]?[\d+/\d+x];
 *
 * @author alisovenko 07.04.15
 */
public class ParseFormulaWithBrackets {
    private static int idx;
    private static String s;

    public static double calculate(String str) {
        idx = 0;
        s = str;

        Tuple t = parseFormula();

        if (s.charAt(idx) != '=') {
            throw new IllegalArgumentException("Wrong formula, found '" + s.charAt(idx) + "' instead of '='");
        }

        idx++;
        Tuple t2 = parseFormula();
        double devisor = (t.x - t2.x);

        double devided = t2.v - t.v;

        System.out.printf("%.2fx = %.2f\n", devisor, devided);
        return devisor == 0 ? 0 : devided / devisor;
    }

    private static Tuple parseFormula() {
        Tuple t = new Tuple();
        skipBlanks();

        while (idx < s.length() && s.charAt(idx) != '=') {
            char sign = parseSign();
            skipBlanks();
            Tuple tuple;

            if (s.charAt(idx) == '(') {
                idx++;
                tuple = parseFormula();
                if (s.charAt(idx) != ')') {
                    throw new IllegalStateException("Wrong formula, missing right bracket!");
                }
                idx++;
            } else if (s.charAt(idx) == ')') {
                // base case: return from recursion. Not increasing the pointer to let calling subroutine check for ending brackets
                break;
            } else {
                tuple = parseExpression();
            }
            t.add(sign == '-' ? tuple.negate() : tuple);
            skipBlanks();
        }
        return t;
    }

    private static Tuple parseExpression() {
        skipBlanks();

        int res = 0;
        Tuple t = new Tuple();
        while (idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
            res += res * 10 + s.charAt(idx) - '0';
            idx++;
        }
        if (idx < s.length() && s.charAt(idx) == 'x') {
            t.x = res;
            idx++;
        } else {
            t.v = res;
        }
        return t;
    }

    private static char parseSign() {
        char c = s.charAt(idx);
        if (idx < s.length() && (c == '-' || c == '+')) {
            idx++;
            return c;
        }
        return '+';
    }

    private static void skipBlanks() {
        while (idx < s.length() && s.charAt(idx) == ' ') {
            idx++;
        }
    }

    private static class Tuple {
        int x;
        int v;

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
        System.out.println(calculate("5x + (4 - 3 - (2x - 5) -4x) = -(3 + 2) + 2x"));
        System.out.println(calculate("5x-((4x  -7x) -( 3-1)) = 0"));
        System.out.println(calculate("5x-((2x + 5)) = 0"));
    }
}

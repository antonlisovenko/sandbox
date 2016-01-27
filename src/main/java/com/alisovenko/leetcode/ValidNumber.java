package com.alisovenko.leetcode;

import com.google.common.base.Preconditions;

import java.util.regex.Pattern;

/**
 * @author alisovenko
 *         12/26/15.
 */
public class ValidNumber {

    // long/int
// float/double
// hex
// octa
// normalized
    public static final Pattern P = Pattern.compile("([-+]?(0|[1-9][0-9]*)l?)|" + // long/int
            "([-+]?((|0|[1-9][0-9]*)(\\.[0-9]+)?[fd]?))|" + // float/double
            "((0x|#)[0-9a-f]+)|" + // hex
            "(0[0-7]+)|" + // octa
            "([-+]?[1-9](\\.[0-9]+)?)e[1-9][0-9]*");

    public static void main(String[] args) {
        System.out.println(Double.valueOf("959440.94f"));
        System.out.println(Float.valueOf("959440.94f"));

        at(matches("342"));
        at(matches("3429809898738979874324"));
        at(matches("00"));
        at(matches("0.000234342"));
        at(matches(".9"));
        at(matches("0.000234342d"));
        at(matches("0.000234342f"));
        at(matches("3423523.000234342f"));
        at(matches("34234234320.453000234342d"));
        at(matches("1.0343e8"));
        at(matches("1.1e8"));
        at(matches("8e898687"));

        af(matches("09"));
        af(matches("0.."));
        af(matches("."));
        af(matches("0423423432.000234342f"));
        af(matches("34234.000234342l"));
        af(matches("10e898687"));
        af(matches("0.13e898687"));
        af(matches("8..e4"));
//        System.out.println(Integer.decode("05"));
    }

    private static boolean matches(String s) {
        return P.matcher(s.trim().toLowerCase()).matches();
    }

    private static void at(boolean b) {
        Preconditions.checkArgument(b);
    }
    private static void af(boolean b) {
        Preconditions.checkArgument(!b);
    }
}

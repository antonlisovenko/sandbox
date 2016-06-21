package com.alisovenko.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alisovenko
 *         2/8/16.
 */
public class Fraction2Decimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "inf";
        }

        if (numerator == 0) {
            return "0";
        }

        String result = "";
        boolean neg = false;

        // Chceck if any negative number
        if ((numerator < 0) ^ (denominator < 0)) {
            neg = true;
        }

        // Transform to long to avoid overflow
        long num = numerator;
        long den = denominator;

        num = Math.abs(num);
        den = Math.abs(den);

        // Get the integer part
        long integer = num / den;
        result = String.valueOf(integer);

        // Get the remindar times 10
        long remindar = (num % den) * 10;
        if (remindar == 0) {
            if (neg) {
                return "-" + result;
            } else {
                return result;
            }
        }

        Map<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";

        while (remindar != 0) {
            if (map.containsKey(remindar)) {
                int pos = map.get(remindar);
                String part1 = result.substring(0, pos);
                String part2 = result.substring(pos, result.length());
                result = part1 + "(" + part2 + ")";

                if (neg) {
                    return "-" + result;
                } else {
                    return result;
                }
            }

            result += String.valueOf(remindar / den);
            map.put(remindar, result.length() - 1);
            remindar = (remindar % den) * 10;
        }

        if (neg) {
            return "-" + result;
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Fraction2Decimal().fractionToDecimal(19, 27));
    }
}

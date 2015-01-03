/*
 * 
 * Created on 18.06.2011
 *
 * $Project$
 * $Workfile$
 * $Revision$
 */

package com.alisovenko.algorithms;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.Collections;

import java.util.BitSet;

import java.util.LinkedList;

import java.util.List;

import java.math.BigDecimal;

/**
 * TBD: add comments for ConvertFromDecimalToBinary.java.
 * 
 * @author <a href="mailto:alisovenko@gmail.com">Anton Lisovenko</a>
 */
public class ConvertFromDecimalToBinaryByte {
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    private static final BigDecimal ONE = BigDecimal.ONE;

    @Test
    public void test() {
        test(347);
        test(945264534);
        test(-3);
        test(-45332);
    }

    private void test(int l) {
        String expected = Integer.toBinaryString(l);
        String actual = parseBigDecimal(l);
        assertEq(expected, actual);
    }

    private void assertEq(String str, String str2) {
        System.out.printf("%-10s: %s \n", "Expected", str);
        System.out.printf("%-10s: %s \n", "Actual", str2);

        assertEquals(str, str2);
    }

    private String parseBigDecimal(long input) {
        BigDecimal decimal = new BigDecimal(input).abs();
        BigDecimal divisor = new BigDecimal(2);
        // Let's match the int
        boolean[] binary = new boolean[32];
        
        int i = binary.length - 1;

        while (true) {
            BigDecimal[] res = decimal.divideAndRemainder(divisor);
            if (res[1].compareTo(BigDecimal.ZERO) > 0) {
                binary[i] = true;
            }
            else {
                binary[i] = false;
            }
            decimal = res[0];

            if (decimal.equals(BigDecimal.ZERO)) {
                break;
            }
            i--;
        }

        // Converting to additional code
        if (input < 0) {
            // 1. Flipping all values
            for (int p = 0; p < binary.length; p++) {
                if (binary[p]) {
                    binary[p] = false;
                }
                else {
                    binary[p] = true;
                }
            }

            // 2. Adding one
            for (int p = binary.length - 1; p >= 0; p--) {
                if (binary[p]) {
                    binary[p] = false;
                }
                else {
                    binary[p] = true;
                    break;
                }
            }
        }

        // 4. String representation ("1003004")
        StringBuilder builder = new StringBuilder();
        boolean removeTrailingZeros = true;
        for (boolean b : binary) {
            if (b) {
                removeTrailingZeros = false;
                builder.append('1');
            }
            else {
                if (!removeTrailingZeros) {
                    builder.append('0');
                }
            }
        }
        return builder.toString();
    }

    /**
     * TBD
     * @param bitSet
     */
    private void addOne(BitSet bitSet) {
        for (int i = bitSet.length() - 1; i >= 0; i--) {
            if (bitSet.get(i)) {
                // all 1 -> 0 until 0 is met
                bitSet.set(i, false);
            }
            else {
                // The first 0 -> 1
                bitSet.set(i);
            }
        }
    }
}

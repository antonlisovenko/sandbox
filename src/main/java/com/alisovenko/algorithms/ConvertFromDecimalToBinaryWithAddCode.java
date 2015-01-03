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
public class ConvertFromDecimalToBinaryWithAddCode {
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal ONE = BigDecimal.ONE;
    public static void main(String[] args) {
        BigDecimal input = new BigDecimal(args[0]);
        int some = -670;
        int some2 = 670;
//        System.out.println(Integer.parseInt("1010011110", 2));
//        System.out.println(Integer.parseInt("10000000000000000000001010011110", 2));
//        System.out.println(Integer.toBinaryString(some));
//        System.out.println(Integer.toBinaryString(some2));
        
        
        
       
        System.out.println();
        System.out.println(Integer.toBinaryString(Integer.parseInt(args[0])));
        
    }
    
    @Test
    public void test() {
        //test(347);
        //test(945264534);
        test(-3);        
    }
    
    private void test (long l) {
        String expected = Long.toBinaryString(l);
        String actual = parseBigDecimal(l);
        assertEq(expected, actual);
    }
    private void assertEq(String str, String str2) {
        System.out.printf("%-10s: %s \n", "Expected", str);
        System.out.printf("%-10s: %s \n", "Actual", str2);
        
        assertEquals(str, str2);
    }
    
    private String parseBigDecimal(long input) {
        BigDecimal decimal = new BigDecimal(input);
        BigDecimal divisor = new BigDecimal(2);
        BitSet bitSet = new BitSet();
        int i = 0;
        
        // 1. Getting the sequence of bits for the input number
        while (true) {
            BigDecimal[] result = decimal.divideAndRemainder(divisor);
            bitSet.set(i++, getBit(result[1].abs()));
            decimal = result[0];
            
            if (decimal.compareTo(ZERO) == 0) {
                break;
            }
        }
        
        // 2. Reversing the bitSet (copy contains all bits except for end "false" ones)
        BitSet copy = (BitSet) bitSet.clone();
        int bitsCnt = copy.length() - 1;
        for (int p = bitsCnt, x = 0; p >= 0; p--, x++) {
            bitSet.set(x, copy.get(p));
        }

        // 3. Processing the negative number
        if (input < 0) {
            // Let's move the bytecode to additional code
//            for (int p = 1; p < bitSet.length(); p++) {
//                bitSet.flip(p);
//            }   

            // Flipping all bits except for the first one
            bitSet.flip(0, bitSet.length() - 1);
            
            addOne(bitSet);     
        }
        
        // 4. String representation ("1003004")
        StringBuilder builder = new StringBuilder();
        for (int p = 0; p <= bitsCnt; p++) {
            builder.append(bitSet.get(p)? "1" : "0");
        }
        return builder.toString();
    }
    /**
     * TBD
     * @param bitSet
     */
    private void addOne(BitSet bitSet) {
        for (int i = bitSet.length() - 1; i >= 0; i-- ) {
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

    private static boolean getBit(BigDecimal num) {
        if (num.compareTo(ZERO) == 0) {
            return false;
        }
        else if (num.compareTo(ONE) == 0) {
            return true;
        }
        throw new IllegalArgumentException();
    }
}

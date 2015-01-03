/*
 * 
 * Created on 18.06.2011
 *
 * $Project$
 * $Workfile$
 * $Revision$
 */

package com.alisovenko.algorithms;

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
public class ConvertFromDecimalToBinary {
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
        
        BigDecimal remainder = ONE;
        BigDecimal divisor = new BigDecimal(2);
        BitSet bitSet = new BitSet();
        int i = 0;
        while (true) {
            BigDecimal[] result = input.divideAndRemainder(divisor);
            bitSet.set(i, getBit(result[1].abs()));
            input = result[0];
            
            if (input.compareTo(ZERO) == 0) {
                break;
            }
            i++;
        }
        
        if (args[0].charAt(0) == '-') {
            // Let's move the bytecode to additional code
            // Flipping all bits except for the first one
            for (int p = bitSet.length()-2; p >= 0; p--) {
                bitSet.flip(p);
            }   
            // Setting the first one to 1
            bitSet.set(bitSet.length() - 1);
            
            int g = bitSet.nextClearBit(0);
            bitSet.set(g);
        }
        
        for (int p = bitSet.length()-1; p >= 0; p--) {
            System.out.print((bitSet.get(p)? "1" : "0"));
        }
        System.out.println();
        System.out.println(Integer.toBinaryString(Integer.parseInt(args[0])));
        
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

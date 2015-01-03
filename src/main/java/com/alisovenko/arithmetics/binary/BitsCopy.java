package com.alisovenko.arithmetics.binary;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Problem: http://www.fulcrumweb.com.ua/archives/999
 * Solution: http://it-interview.org/archives/2049
 * 
 * We need to copy the range of bits from b to a. The range is from startBit to stopBit.
 * 
 * <p>Created: 23.07.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BitsCopy {
    @Test
    public void test() {
        assertEquals(229, copy_bits(245, 4, 1, 4));
        assertEquals(197, copy_bits(245, 4, 1, 5));
        assertEquals(229, copy_bits(245, 5, 1, 4));
    }
    int copy_bits(int a, int b, int startBit, int stopBit)
    {
        int ones = ~1;
        int mask = ones << startBit; // Shifting to startBit
        int second = ones << stopBit; // Shifting to stopBit
        mask ^= second; // Ne between them
        print ("Mask", mask);
        
        print ("A", a);
        print ("B", b);
        
        // Leaving only bits in mask (0000001111000)
        b = b & mask;
        print ("B (masked)", b);
        
        // Leaving all bits except for those in mask (1111110000111) 
        a = a & (~mask);
        print ("A (masked)", a);
        
        int result =  a | b;
        
        print ("Result" , result);
        return result;
    }
    
    private static void print(String name , int i) {
        System.out.println(name + ": " + Integer.toBinaryString(i));
    }

}

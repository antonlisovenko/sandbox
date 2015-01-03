package com.alisovenko.arithmetics.binary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.BitSet;

/**
 * http://it-interview.org/archives/1894
 * 
 * We need to check the integer that it matches the mask. * The format of mask is "11110000000000000000000000000000" or
 * "11111111111100000000000000000000" (leading 1's and following 0's)
 * 
 * <p>
 * Created: 27.07.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BitMaskCheck {
    @Test
    public void testMask() {
        BitSet sample = new BitSet();
        sample.set(25, 32);

        assertTrue(check(sample));

        sample.set(10, 32);

        assertTrue(check(sample));

        sample.set(25, 30);

        assertFalse(check(sample));

        sample.set(1, 5);

        assertFalse(check(sample));

        sample.set(5);
        assertFalse(check(sample));
    }

    public boolean check(BitSet bitset) {
        boolean result = checkMask2((int) bitset.toLongArray()[0]);
        bitset.clear();

        return result;
    }

    /**
     * We generate all types of mask (from 1 to 31) using bit shift and compare the input with it.
     */
    public boolean checkMask(int sample) {
        boolean result2 = (sample >> 1 | sample) << 1 == sample;

        int mask = ~0;
        System.out.println("Sample:\n" + Integer.toBinaryString(sample));
        for (int i = 0; i < 32; i++) {
            System.out.println(Integer.toBinaryString(mask));
            mask = mask & (mask << 1);
            if (sample == mask) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clever way
     */
    public boolean checkMask2(int sample) {
        // Doesn't work for 111111111111111111111
        boolean result = (sample >> 1 | sample) << 1 == sample;

        // Works for all numbers
        boolean result2 = (sample << 1 | sample) == sample;
        return result2;
    }

}

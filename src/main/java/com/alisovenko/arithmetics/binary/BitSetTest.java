package com.alisovenko.arithmetics.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.BitSet;

/**
 * Tests the {@link BitSet} class
 * 
 * <p>
 * Created: 02.06.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BitSetTest {
    @Test
    public void test() {
        // 1. If we shift long more than 64 - all bits are cut and long is started again
        long x = (1L << 66);
        long y = (1L << 2);
        assertEquals(4, x);
        assertEquals(x, y);

        // 2. That is the principle in BitSet actually: it keeps the array of longs and for each operation calculates
        // the element it needs. Than shifts bits to left for this word
        // Imitating bitSet.set(200):

        long[] set = new long[4];

        // The forth element keeps the necessary grade. Applying bit OR to set the bit to true.
        set[3] |= (1L << 200);
        assertEquals(set[3], 256);

        BitSet bitSet = new BitSet(260);

        bitSet.set(200);

        assertTrue(bitSet.get(200));
    }

}

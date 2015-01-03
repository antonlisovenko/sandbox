package com.alisovenko.algorithms.projecteuler;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * http://projecteuler.net/problem=5
 * 
 * There is the mathematical solution!
 * http://projecteuler.net/thread=5
 * 
 * <p>Created: 27.07.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class Problem5 {    
    @Test
    public void test() {
        int result = 0;
        for (int i = 20; i < Integer.MAX_VALUE; i = i + 20) {
            if (isDivisableBy20(i)) {
                result = i;
                break;
            }
        }
        
        assertEquals(232792560, result);
    }

    /**
     * TBD
     * @param i
     * @return
     */
    private boolean isDivisableBy20(int num) {
        // We use only [20-11] range as others (11-2) are the factors of previous ones.
        // Also we start from 20 as this has the best changes to finish beforehand
        for (int i = 20; i > 10; i--) {
            if (num % i != 0) {
                return false;
            }
        }
        
        return true;
    }
}

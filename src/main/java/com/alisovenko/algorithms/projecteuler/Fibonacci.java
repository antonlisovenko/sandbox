package com.alisovenko.algorithms.projecteuler;

import org.junit.Test;

/**
 * See http://projecteuler.net/problem=2
 * 
 * <p>Created: 25.07.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class Fibonacci {
    @Test
    public void testSumm() {
        int i = 1;
        int y = 2;
        long summ = 0;
        
        while (y <= 4000000) {
            //System.out.println("i : " + i + ", y : " + y);
            
            if (isEven(y)) {
                summ += y;
            }
            y = y + i;
            // previous element
            i = y - i;
            
            
        }
        System.out.println(summ);
    }

    /**
     * TBD
     * @param y
     * @return
     */
    private boolean isEven(int y) {
        if (y % 2 == 0) {
            return true;
        }
        return false;
    }

}

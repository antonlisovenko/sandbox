package com.alisovenko.algorithms.projecteuler;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * http://projecteuler.net/problem=6
 * 
 * <p>Created: 27.07.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class Problem6 {    
    @Test
    public void test() {
        int n = 100;
        // Sum of squares
        int sumSqrs = 0;
        for(int i = 1; i <= n; i++) {
            
            sumSqrs += i*i;
        }
        
        // Square of summs (summ of arithmetic progression)
        double base = (double)(1 + 100)/ 2 * 100;
        int sqrSum = (int) (base * base);
        
        System.out.printf("sum of squares:%s\n", sumSqrs); 
        System.out.printf("base:%s\n", base); 
        System.out.printf("square of summs:%s\n", sqrSum);
        
        assertEquals(25164150, sqrSum - sumSqrs);
    }
}

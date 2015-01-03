package com.alisovenko.algorithms.polynomial;

import org.junit.Test;

/**
 * See 2.3 problem (Kormen)
 *
 * <p>Created: 24.03.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class HornersRule {
    @Test
    public void testHornerPolynomial() {
        int res = 0;
        int x = 5;
        int temp = 0;
        
        for (int i = 3; i>=0; i--) {
            res = i + x * res;
        }
        /*for (int i = 0, j = 3; i <= 3 && j >=0; i++, j--) {
            res = res + j;
            res = res + (x * res);
        }*/
        
        System.out.println(res);
    }

}

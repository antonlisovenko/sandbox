package com.alisovenko.algorithms;

import java.util.ArrayList;

import java.util.List;

/**
 * http://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%AD%D1%80%D0%B0%D1%82%D0%BE%D1%81%D1%84%D0%B5%D0%BD%D0%B0
 * 
 * Created: Jun 15, 2011
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class EratosphenSieve {
    public static void main(String[] args) {
        long n=1000;
        List<Boolean> cells = new ArrayList<Boolean>(0);
        List<Integer> result = new ArrayList<Integer>();
        
        // 1. Initializing the array with true (isPrime) values
        for (int i = 0; i < n; i++) {
            cells.add(i, true);
        }
        
        boolean proceedOnlyNonClosed = false;
        // 2. Running through the array starting from 2
        for (int p=2; p < n; p++) {
            // The condition for finish: p squared is bigger than n
            if (proceedOnlyNonClosed || p * p > n) {
                proceedOnlyNonClosed = true;
                if (cells.get(p)) {
                    result.add(p);
                    //break;
                }
            }
            // If the cell is not crossed - this is prime
            else if (cells.get(p)) {
                result.add(p);
                // Once we found the prime - we walk through the rest of array with step p and cross all of them
                // after this we get the next non-crossed element, consider it prime and walk through the rest with new p value
                for (int j=(int)Math.pow(p,2); j<n; j+=p) {
                    cells.set(j, false);                    
                }
            }
        }
        
        System.out.println(result);
    }
    

}

package com.alisovenko.algorithms.projecteuler;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * http://projecteuler.net/problem=3
 * http://stackoverflow.com/questions/201374/project-euler-question-3-help?lq=1
 * 
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * (c)
 * 
 * The second (better) way is to build Eratosphen sieve up to sqrt(n) and then check factors of n to be prime
 * Created: Jun 15, 2011
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class EratosphenSieveBiggestPrime {
    @Test
    public void testPrimeFactor () {
        long n=600851475143L;
        
        int biggest = 0;
        for (int i = (int) Math.sqrt(n); i> 0; i--) {
            if (n % i == 0) {
                // This is a factor
                if (isPrime(i) && i > biggest) {
                    biggest = i;
                }
                
                // Let's check the complementary
                int p = (int) (n/i);
                if (isPrime(p) && p > biggest) {
                    biggest = p;
                }
            }
        }

        assertEquals(6857, biggest);
    }
    /**
     * @param p
     * @return
     */
    private boolean isPrime(int p) {
        for (int i = 2; i < p; i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

}

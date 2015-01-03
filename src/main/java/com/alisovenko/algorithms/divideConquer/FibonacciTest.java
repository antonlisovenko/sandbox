package com.alisovenko.algorithms.divideConquer;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Fibonacci recursion. Solves the problem in exponential O
 *
 *
 * <p>Created: 19.04.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class FibonacciTest {
    @Test
    public void test() throws InterruptedException {
        System.out.println(calculate(50));
    }
    public BigInteger calculate(int n)
        throws InterruptedException {
      if (Thread.interrupted()) throw new InterruptedException();
      if (n < 0) throw new IllegalArgumentException();
//      System.out.println("Current n: " + n);
      if (n == 0) return BigInteger.ZERO;
      if (n == 1) return BigInteger.ONE;
      // Here for 10 the first calculate will be called 10 times, the second - 9 times. 
      return calculate(n - 1).add(calculate(n - 2));
    }
}

package com.alisovenko.indeed;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author alisovenko 10.01.15
 */
public class RandomGenerator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger A = scanner.nextBigInteger();
        BigInteger B = scanner.nextBigInteger();
        BigInteger X = scanner.nextBigInteger();
        BigInteger K = scanner.nextBigInteger();
        BigInteger M = scanner.nextBigInteger();

        BigInteger _1 = calculate(K.add(new BigInteger("-1")), A, B, X, M);
        BigInteger _2 = calculate(K, A, B, X, M);
        BigInteger _3 = calculate(K.add(new BigInteger("1")), A, B, X, M);
        BigInteger _4 = calculate(K.add(new BigInteger("2")), A, B, X, M);
        BigInteger _5 = calculate(K.add(new BigInteger("3")), A, B, X, M);

        System.out.println(_1);
        System.out.println(_2);
        System.out.println(_3);
        System.out.println(_4);
        System.out.println(_5);
    }

    private static BigInteger calculate(BigInteger k, BigInteger a, BigInteger b, BigInteger x, BigInteger m) {
        BigInteger a1 = a.subtract(BigInteger.ONE);  // a - 1
        BigInteger ma = a1.multiply(m);              // (a - 1) * m
        BigInteger y = a.modPow(k, ma).subtract(BigInteger.ONE).divide(a1).multiply(b);  // (a^n - 1) / (a - 1) * b, sort of
        BigInteger z = a.modPow(k, m).multiply(x);   // a^n * x, sort of
        x = y.add(z).mod(m);  // (y + z) mod m
        return x;
    }
}

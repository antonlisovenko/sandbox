package com.alisovenko.indeed;

import java.util.Scanner;

/**
 * @author alisovenko 10.01.15
 */
public class RandomGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int X = scanner.nextInt();
        int K = scanner.nextInt();
        int M = scanner.nextInt();

        int _1 = calculate(K, A, B, X, M);
        int _2 = calculate(K + 1, A, B, X, M);
        int _3 = calculate(K + 2, A, B, X, M);
        int _4 = calculate(K + 3, A, B, X, M);
        int _5 = calculate(K + 4, A, B, X, M);

        System.out.println(_1);
        System.out.println(_2);
        System.out.println(_3);
        System.out.println(_4);
        System.out.println(_5);
    }

    private static int calculate(int k, int a, int b, int x, int m) {
//        int a1 = a - 1;
//        int ma = a1 * m;
//        int y = ((int)Math.pow(a, ma) - 1) / a1 * b;
//        int z = (int)Math.pow(a, k) * x;
//        return (y + z) % m;
        int pow = (int) Math.pow(a, k);
        return ((pow * x)% m + (((pow - 1)/(a - 1) * b))) % m;
//        return ((pow * x)% m + (((pow - 1) % ((a - 1)*m))/(a - 1)) * b) % m;
    }
}

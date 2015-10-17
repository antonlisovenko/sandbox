package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 01.02.15
 */
public class PowerUsingSum {
    public static int recursiveAdd(int n, int m) {
        if (m == 0) {
            return 0;
        }
        if ( (m & 1) == 1) {
            return recursiveAdd(n, m - 1) + n;
        }
        if (m / 2 >= 2) {
            int a = recursiveAdd(n, m / 2);
            return  a + a;
        }
        // base case: we got the couple
        return n + n;
    }

    public static int square(int n) {
        return recursiveAdd(n, n);
    }

    public static void main(String[] args) {
        System.out.println(square(2));
        System.out.println(square(3));
        System.out.println(square(4));
        System.out.println(square(5));
        System.out.println(square(6));
        System.out.println(square(7));
        System.out.println(square(8));
        System.out.println(square(9));
    }
}

package com.alisovenko.leetcode;

/**
 * @author alisovenko 24.01.15
 */
public class UniqueBSTs {
    public static int count (int n) {
        // base case: only one node
        if (n <= 1) {
            return 1;
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int s = count(i-1) * count(n - i);
            sum += s;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(count(3));
        System.out.println(count(4));
        System.out.println(count(5));
        System.out.println(count(6));
        System.out.println(count(7));
        System.out.println(count(8));
        System.out.println(count(9));
        System.out.println(count(50));
    }
}

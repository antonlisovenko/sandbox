package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 14.03.15
 */
public class FindKNotNegativeSum {
    public static int findK(int[] a, int[] b) {
        int[] c = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] - b[i];
        }

        int k = 0; // start pointer
        int sum = 0; // total sum
        int i = 0; // end pointer

        while (i - k < c.length) {
            sum += c[i % c.length];
            i++;

            // supporting the invariant "sum > 0"
            while (sum < 0 && k <= i) {
                sum -= c[k];
                k++;
            }
        }
        check(c, k);
        return k;
    }

    public static void main(String[] args) {
        System.out.println(findK(new int[]{1, 1,1,8,5}, new int[]{7, 5, 2, 1, 1}));
        System.out.println(findK(new int[]{2, 3, 2, 2, 3, 3}, new int[]{3, 2, 3, 3, 2, 2}));
        System.out.println(findK(new int[]{1, 1, 5, 8, 1}, new int[]{0, 0, 0, 1, 15}));
        System.out.println(findK(new int[]{5, 4, 3, 2, 1}, new int[]{1, 2, 3, 4, 5}));
    }

    private static void check(int[] c, int k) {
        int sum = 0;
        for (int i = k; i < c.length + k; i++) {
            sum += c[i % c.length];
            assert sum >= 0;
        }
    }
}

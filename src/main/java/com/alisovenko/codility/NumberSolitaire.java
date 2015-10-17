package com.alisovenko.codility;

/**
 * @author alisovenko 09.03.15
 */
public class NumberSolitaire {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A == null || A.length == 0)
            return 0;
        if (A.length == 1)
            return A[0];

        int[] cache = new int[A.length]; // i element indicates the maximum sum in case 0..i elements were included

        cache[0] = A[0];

        for(int i = 1; i < cache.length; i++) {
            int maxSum = Integer.MIN_VALUE;
            // p is the size of step "backwards"
            for (int p = 1; p < 7; p++) {
                // checking that we have place to step back
                if (i - p >= 0) {
                    maxSum = Math.max(cache[i-p] + A[i], maxSum);
                }
            }
            cache[i] = maxSum;
        }

        return cache[A.length - 1];

    }

    public static void main(String[] args) {
        NumberSolitaire numberSolitaire = new NumberSolitaire();

        System.out.println(numberSolitaire.solution(new int[]{-2, -1}));
        System.out.println(numberSolitaire.solution(new int[]{2, -1, 6,}));
    }
}

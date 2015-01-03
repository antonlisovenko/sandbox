package com.alisovenko.algorithms.divideConquer;

import java.util.Random;
import org.junit.Test;

/**
 * Performs the lg(n)n searching of the consequent subarray with maximum total summ.
 * 
 * <p>
 * Created: 29.03.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class MaximumSubarray {
    @Test
    public void test() {
        // {-4, 2, 8, -6, -3, 4, 2, -1, 10}
        Random rand = new Random();
        int number = 100000;
        int[] input = new int[number];
        for (int i = 0; i < number; i++) {
            input[i] = rand.nextBoolean() ? (-1) * rand.nextInt(5) : rand.nextInt(5);
        }

        long t = System.currentTimeMillis();
        System.out.println(getInefficiently(input));
        System.out.println(System.currentTimeMillis() - t);
        
        t = System.currentTimeMillis();
        System.out.println(recursiveFind(0, input.length - 1, input));
        System.out.println(System.currentTimeMillis() - t);

    }

    public Tuple recursiveFind(int left, int right, int[] input) {
        if ((right - left) == 0) {
            return new Tuple(left, right, input[right]);
        }
        int medium = (right + left) / 2;
        Tuple leftTuple = recursiveFind(left, medium, input);
        Tuple rightTuple = recursiveFind(medium + 1, right, input);
        Tuple crossTuple = performCrossFind(left, right, input);

        if (leftTuple.summ >= rightTuple.summ && leftTuple.summ >= crossTuple.summ) {
            return leftTuple;
        }
        else if (rightTuple.summ >= leftTuple.summ && rightTuple.summ >= crossTuple.summ) {
            return rightTuple;
        }
        else {
            return crossTuple;
        }
    }

    public Tuple performCrossFind(int leftI, int rightI, int[] input) {
        int medium = (leftI + rightI) / 2;

        int leftSumm = Integer.MIN_VALUE, tempSumm = 0, left = 0, right = 0, rightSumm = Integer.MIN_VALUE;

        for (int i = medium; i >= leftI; i--) {
            tempSumm += input[i];
            if (tempSumm >= leftSumm) {
                leftSumm = tempSumm;
                left = i;
            }
        }
        tempSumm = 0;
        for (int i = medium + 1; i <= rightI; i++) {
            tempSumm += input[i];
            if (tempSumm > rightSumm) {
                rightSumm = tempSumm;
                right = i;
            }
        }
        return new Tuple(left, right, leftSumm + rightSumm);
    }

    /**
     * Performs straightforward calculation (O(n2))
     * @param input
     * @return
     */
    private Tuple getInefficiently(int[] input) {
        Tuple tuple = new Tuple();
        int summ = 0;
        int s = 0;
        for (int i : input) {
            for (int p = s; p < input.length; p++) {
                summ += input[p];
                if (summ > tuple.summ) {
                    tuple = new Tuple(s, p, summ);
                }
            }
            summ = 0;
            s++;
        }
        return tuple;
    }

    private static class Tuple {
        public Tuple() {
            // TODO Auto-generated constructor stub
        }

        public Tuple(int start, int end, int summ) {
            super();
            this.start = start;
            this.end = end;
            this.summ = summ;
        }

        int start;

        int end;

        int summ;

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Tuple [start=" + start + ", end=" + end + ", summ=" + summ + "]";
        }

    }

}

package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         4/1/16.
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        long[] data = new long[(int)((1l << 32) / 64)];

        for (int i: nums) {
            int l = i / 64;
            long shift = 1l << (i % 64);
            if ((data[l] & shift) == shift) data[l] &= ~shift;
            else data[l] |= shift;
        }
        int[] res = new int[2];
        int i = 0;
        for (int p = 0; p < data.length; p++) {
            if (data[p] > 0) //
                for (int j = 0; j < 64; j++) {
                    if ((data[p] >> j & 1) == 1) {
                        res[i++] = p * 64 + j;
                    }
                }
            if(i == 2) break;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SingleNumber3().singleNumber(new int[]{111, 200, 111, 300, 200, 500})));
    }
}

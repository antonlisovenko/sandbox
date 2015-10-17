package com.alisovenko.leetcode;

/**
 * @author alisovenko 03.07.15
 */
public class _3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length <= 2) {
            return 0;
        }

        long offset = Long.MAX_VALUE;
        long sum = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int p = 0; p < nums.length - 1; p++) {
                long sum2 = nums[i] + nums[p];
                for (int s = 0; s < nums.length; s++) {
                    sum = sum2 + nums[s];
                    offset = Math.min(offset, Math.abs(sum - target));
                }
            }
        }

        if (offset > Integer.MAX_VALUE || offset < Integer.MIN_VALUE) {
            throw new RuntimeException("Wrong input");
        }
        return (int) offset;
    }
}

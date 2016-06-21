package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         2/24/16.
 */
public class _2Sums {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n; i++) nums[i] = nums[i] * n + (nums[i] < 0 ? -i : i);

        Arrays.sort(nums);

        int lo = 0, hi = n - 1;

        while (lo < hi) {
            int sum = nums[lo]/n + nums[hi]/n;
            if (sum == target) return new int[]{nums[lo]%n < 0 ? -nums[lo]%n : nums[lo]%n, nums[hi]%n < 0 ? -nums[hi]%n : nums[hi]%n};
            if (sum < target)   lo++;
            else                hi--;
        }
        throw new IllegalArgumentException();
    }
}

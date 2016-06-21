package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         1/30/16.
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // First, find the nums[] element that is greater or equal to lower bound
        int i = 0;
        for (; i < nums.length && nums[i] < lower; i++) {}

        List<String> result = new ArrayList<>();
        int c = lower;

        for (int p = i; p < nums.length && nums[p] <= upper; p++) {
            if (nums[p] == c) {
                c++;
            } else {
                result.add(nums[p] > c + 1 ? String.format("%s->%s", c, nums[p] - 1) : "" + c);
                c = nums[p] + 1;
            }
        }

        // tail
        if (c <= upper) {
            result.add(upper > c ? String.format("%s->%s", c, upper) : "" + c);
        }
        return result;
    }
}

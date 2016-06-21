package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         2/21/16.
 */
public class _3SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        Arrays.sort(nums);

        int max = nums[n - 1];
        int cnt = 0;

        for (int i = 0; i < n - 2; i++) {
            for (int p = i + 1; p < n - 1; p++) {
                int sum = nums[i] + nums[p];
                int left = target - sum;
                if (left > max) {
                    cnt += n - p - 1;
                    System.out.printf("(1) i: %d, p: %d, cnt: %d, left: %d\n", i, p, cnt, left);
                } else {
                    // BS for the position of the last number less than 'left'
                    cnt += floor(nums, p + 1, n - 1, left) - p - 1;
                    System.out.printf("(2) i: %d, p: %d, cnt: %d, left: %d\n", i, p, cnt, left);
                }
            }
        }
        return cnt;
    }
    private int floor(int[] nums, int lo, int hi, int k) {
        while (hi >= lo) {
            int med = lo + (hi - lo) / 2;
            if (nums[med] >= k) hi = med - 1;
            else lo = med + 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new _3SumSmaller().threeSumSmaller(new int[]{-1,1,-1,-1}, -1));
    }
}

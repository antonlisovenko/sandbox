package com.alisovenko.leetcode;

import com.google.common.base.Stopwatch;

import java.util.*;

/**
 * @author alisovenko
 *         3/20/16.
 */
public class _3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return Collections.emptyList();

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (lo > i + 1 && nums[lo - 1] == nums[lo])
                    lo++;
                else if (hi < nums.length - 1 && nums[hi + 1] == nums[hi])
                    hi--;
                else {
                    long sum = nums[lo] + nums[hi];
                    if (sum == -nums[i])
                        ans.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                    else if (sum < -nums[i])
                        lo++;
                    else
                        hi--;
                }
            }
        }

        return ans;
    }

    public List<List<Integer>> bruteForce(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return new ArrayList<>(ans);
    }

    int[][] m;
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int m = n + 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) nums[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            int prev = nums[i] % m;
            if (prev > 0)
                nums[prev - 1] = (prev * m) + nums[prev - 1] % m;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] / m != i + 1) return i + 1;
        }
        return m;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(new _3Sum().threeSum(new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6}).size());
//        System.out.println(new _3Sum().bruteForce(new int[]{7, -1, 14, -12, -8, 7, 2, -15, 8, 8, -8, -14, -4, -5, 7, 9, 11, -4, -15, -6, 1, -14, 4, 3, 10, -5, 2, 1, 6, 11, 2, -2, -5, -7, -6, 2, -15, 11, -6, 8, -4, 2, 1, -1, 4, -6, -15, 1, 5, -15, 10, 14, 9, -8, -6, 4, -6, 11, 12, -15, 7, -1, -9, 9, -1, 0, -4, -1, -12, -2, 14, -9, 7, 0, -3, -4, 1, -2, 12, 14, -10, 0, 5, 14, -1, 14, 3, 8, 10, -8, 8, -5, -2, 6, -11, 12, 13, -7, -12, 8, 6, -13, 14, -2, -5, -11, 1, 3, -6}).size());
        System.out.println(stopwatch.stop());
    }
}

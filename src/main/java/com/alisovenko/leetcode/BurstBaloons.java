package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/18/16.
 */
public class BurstBaloons {
            public int maxCoins(int[] nums) {
                return findRecursively(nums, 0, new int[nums.length][nums.length]);
            }

            private int findRecursively(int[] nums, int idx, int[][] cache) {
                if (idx >= nums.length) {
        //            System.out.println(Arrays.toString(nums));
                    return 0;
                }
                int max = 0;
                for (int i = idx; i < nums.length; i++) {
                    int sum = calc(nums, idx, i, cache);
                    swap(nums, idx, i);

                    sum += findRecursively(nums, idx + 1, new int[nums.length][nums.length]);
        //            System.out.printf("nums: %s, current: %s, sum: %s, i: %s, idx: %s\n", Arrays.toString(nums), calc(nums, idx, i), sum, i, idx);
                    max = Math.max(sum, max);

                    swap(nums, i, idx);
                }
                return max;
            }

            private int calc(int[] nums, int start, int idx, int[][] cache) {
                int first = idx == start || nums.length <= 1 ? -1 : idx - 1;
                int last = idx == nums.length - 1 ? -1 : idx + 1;
                if (first < 0) {
                    if (last < 0) {
                        return nums[idx];
                    }
                    cache[idx][last] = cache[idx][last] > 0 ? cache[idx][last] : nums[idx] * nums[last];
                    return cache[idx][last];
                }
                if (last < 0) {
                    cache[first][idx] = cache[first][idx] > 0 ? cache[first][idx] : nums[first] * nums[idx];
                    return cache[first][idx];
                }
                if (cache[first][idx] > 0) return cache[first][idx] * nums[last];
                if (cache[idx][last] > 0) return cache[idx][last] * nums[first];

                cache[first][idx] = nums[first] * nums[idx];
                cache[idx][first] = cache[first][idx];

                return cache[idx][first] * nums[last];
            }

            private void swap(int[] nums, int i, int p) {
                int t = nums[i];
                nums[i] = nums[p];
                nums[p] = t;
            }

    public static void main(String[] args) {
        System.out.println(new BurstBaloons().maxCoins(new int[]{3, 1, 5, 8}));
    }
}

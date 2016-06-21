package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/28/16.
 */
public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        long miss = 1, added = 0;
        int i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                System.out.printf("miss: %s, num: %s\n", miss, nums[i]);
                miss += nums[i++];
            } else {
                System.out.printf("new: %s -> %s\n", miss, miss + miss);
                miss += miss;
                added++;
            }
        }
        return (int) added;
    }

    public static void main(String[] args) {
        System.out.println(new PatchingArray().minPatches(new int[]{1, 2, 4, 13, 43}, 100));
    }
}

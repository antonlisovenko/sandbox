package com.alisovenko.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author alisovenko
 *         4/5/16.
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        permutations(nums, 0, ans);

        return new ArrayList<>(ans);
    }

    void permutations(int[] nums, int idx, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>() {{
                for (int i : nums) add(i);
            }});
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (!isDuplicate(nums, idx, i)) {
                swap(nums, idx, i);
                permutations(nums, idx + 1, ans);
                swap(nums, idx, i);
            }
        }
    }

    void swap(int[] nums, int from, int to) {
        int t = nums[from];
        nums[from] = nums[to];
        nums[to] = t;
    }

    public boolean isDuplicate(int[] nums, int begin, int i) {
        for (int a = begin; a < i; a++) {
            if (nums[a] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Permutations2().permuteUnique(new int[]{3, 3, 1, 2, 3, 2, 3, 1}).size());
    }
}

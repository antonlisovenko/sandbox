package com.alisovenko.leetcode;

import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author alisovenko
 *         12/28/15.
 */
public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        NavigableSet<Integer> window = new TreeSet<>();
        int start = 0, end = 0;

        while (end < nums.length) {
            if (end - start <= k) {
                if (addAndCheck(window, nums[end], t)) return true;
                end++;
            } else {
                window.remove(nums[start++]);
            }
        }
        return false;
    }

    private boolean addAndCheck(NavigableSet<Integer> window, int num, int t) {
        if (!window.add(num)) return true;
        return (window.higher(num) != null && ((long)window.higher(num) - num) <= t) ||
                (window.lower(num) != null && ((long)num - window.lower(num)) <= t);
    }
}

package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         1/15/16.
 */
public class UglyNumber {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }

        List<Integer> nums = new ArrayList<>();
        nums.add(1);

        int i = 0;
        int j = 0;
        int k = 0;

        while (nums.size() < n) {
            int m2 = nums.get(i) * 2;
            int m3 = nums.get(j) * 3;
            int m5 = nums.get(k) * 5;

            int mn = Math.min(Math.min(m2, m3), m5);
            nums.add(mn);

            if (mn == m2) {
                i++;
            }

            if (mn == m3) {
                j++;
            }

            if (mn == m5) {
                k++;
            }
        }

        System.out.println(nums);
        return nums.get(nums.size() - 1);
    }

    public static void main(String[] args) {
        new UglyNumber().nthUglyNumber(10);
    }
}

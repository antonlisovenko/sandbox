package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * My rewrite of http://buttercola.blogspot.ie/2014/09/leetcode-median-of-two-sorted-arrays.html
 * @author alisovenko
 *         2/2/16.
 */
public class MedianOfSortedArrays3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;

        if (n % 2 == 1) return findMedianRecursively(nums1, nums2, n / 2);
        return (findMedianRecursively(nums1, nums2, n / 2 - 1) + findMedianRecursively(nums1, nums2, n / 2))/2;
    }

    private double findMedianRecursively(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0) return nums2[k];
        if (nums2.length == 0) return nums1[k];
        if (k == 0) return Math.min(nums1[0], nums2[0]);

        int n = nums1.length / 2;
        int m = nums2.length / 2;

        if (nums1[n] < nums2[m]) {
            // Cutting the left part of nums1
            if (n + m < k) return findMedianRecursively(Arrays.copyOfRange(nums1, n + 1, nums1.length), nums2, k - n - 1);
                // else cutting the right part of nums2
            else return findMedianRecursively(nums1, Arrays.copyOfRange(nums2, 0, m), k);
        } else {
            // Cutting the left part of nums2
            if (n + m < k) return findMedianRecursively(nums1, Arrays.copyOfRange(nums2, m + 1, nums2.length), k - m - 1);
                // else cutting the right part of nums1
            else return findMedianRecursively(Arrays.copyOfRange(nums1, 0, n), nums2, k);
        }
    }
}

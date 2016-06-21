package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         2/2/16.
 */
public class MedianOfSortedArrays2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.f;
        }

        int n1 = nums1.length;
        int n2 = nums2.length;

        if ((n1 + n2) % 2 == 1) {
            return findMedianHelper(nums1, nums2, (n1 + n2) / 2 + 1);
        } else {
            return (findMedianHelper(nums1, nums2, (n1 + n2) / 2) + findMedianHelper(nums1, nums2, (n1 + n2) / 2 + 1)) / 2;
        }
    }

    private double findMedianHelper(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0) {
            return nums2[k - 1];
        }

        if (nums2 == null || nums2.length == 0) {
            return nums1[k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }

        int n1 = nums1.length;
        int n2 = nums2.length;

        System.out.printf("%-20s, %-20s; median: %s (#%s), median: %s (#%s), k: %s\n", Arrays.toString(nums1), Arrays.toString(nums2), nums1[n1/2], n1/2, nums2[n2/2], n2/2, k);
        if (nums1[n1 / 2] > nums2[n2 / 2]) {
            if ((n1 / 2 + n2 / 2 + 1) >= k) {
                return findMedianHelper(Arrays.copyOfRange(nums1, 0, n1 / 2), nums2, k);
            } else {
                return findMedianHelper(nums1, Arrays.copyOfRange(nums2, n2 / 2 + 1, n2), k - (n2 / 2 + 1));
            }
        } else {
            if ((n1 / 2 + n2 / 2 + 1) >= k) {
                return findMedianHelper(nums1, Arrays.copyOfRange(nums2, 0, n2 / 2), k);
            } else {
                return findMedianHelper(Arrays.copyOfRange(nums1, n1 / 2 + 1, n1), nums2, k - (n1 / 2 + 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfSortedArrays2().findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8, 9}));
    }
}

package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/2/16.
 */
public class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) return median(nums2, 0, nums2.length - 1);
        if (nums2.length == 0) return median(nums1, 0, nums1.length - 1);
        int x1 = 0, x2 = nums1.length - 1;
        int y1 = 0, y2 = nums2.length - 1;

        while (true) {
            double m1 = median(nums1, x1, x2);
            double m2 = median(nums2, y1, y2);
            if (m1 == m2) return m1;
            boolean is1odd = (x2 - x1 + 1) % 2 == 1;
            boolean is2odd = (y2 - y1 + 1) % 2 == 1;

            int m1Idx = medIdx(x1, x2);
            int m2Idx = medIdx(y1, y2);

            if (x2 - x1 < 3 || y2 - y1 < 3) return mergeAndFindMedian(nums1, x1, x2, nums2, y1, y2);

            if (m1 < m2) {
                x1 = is1odd ?  m1Idx : m1Idx - 1;
                y2 = m2Idx;
            } else {
                x2 = m1Idx;
                y1 = is2odd ? m2Idx : m2Idx - 1;
            }
        }
    }
    private double mergeAndFindMedian(int[] nums1, int x1, int x2, int[] nums2, int y1, int y2) {
        int n = x2 - x1 + 1;
        int m = y2 - y1 + 1;
        int[] merged = new int[n + m];

        int i1 = x1, i2 = y1, i = 0;

        while (i1 <= x2 || i2 <= y2) {
            if (i1 > x2) merged[i++] = nums2[i2++];
            else if (i2 > y2) merged[i++] = nums1[i1++];
            else if (nums1[i1] < nums2[i2]) merged[i++] = nums1[i1++];
            else merged[i++] = nums2[i2++];
        }
        return median(merged, 0, merged.length - 1);
    }
    private int medIdx(int from, int to) {
        return from + (to - from + 1)/2;
    }
    private double median(int[] arr, int from, int to) {
        int idx = medIdx(from, to);
        if ((to - from + 1) % 2 == 1) return (double) arr[idx];
        return (double)(arr[idx] + arr[idx - 1]) / 2;
    }

    public static void main(String[] args) {
//        System.out.println(new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 5, 6}, new int[]{0, 4, 8}));// 0, 1, 4, 5, 6, 8 -> 4.5
//        System.out.println(new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 5, 6, 10}, new int[]{0, 4, 8}));// 0, 1, 4, 5, 6, 8, 10 -> 5
//        System.out.println(new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 5, 6, 10}, new int[]{0, 4, 8, 12}));// 0, 1, 4, 5, 6, 8, 10, 12 -> 5.5
//        System.out.println(new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 3, 7, 8}, new int[]{2, 4, 5, 6}));// 1, 2, 3, 4, 5, 6, 7, 8 -> 4.5
        System.out.println(new MedianOfSortedArrays().findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8, 9}));// 1, 2, 3, 4, 5, 6, 7, 8, 9 -> 5.0
    }
}

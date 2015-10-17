package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko 12.04.15
 */
public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0]  = -1;

        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[i - 1]) {
                lessFromLeft[i] = i - 1;
            }
            else  {
                int p = i - 1;

                while (p >= 0 && height[p] >= height[i]) {
                    p = lessFromLeft[p];
                }
                lessFromLeft[i] = p;
            }
        }

        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] > height[i + 1]) {
                lessFromRight[i] = i + 1;
            }
            else  {
                int p = i + 1;

                while (p < height.length && height[p] >= height[i]) {
                    p = lessFromRight[p];
                }
                lessFromRight[i] = p;
            }
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }


        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{1, 1}));
        System.out.println(largestRectangleArea(new int[]{0, 2, 0}));

    }
}

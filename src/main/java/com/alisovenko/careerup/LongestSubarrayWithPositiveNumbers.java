package com.alisovenko.careerup;

import java.util.Arrays;

/**
 * http://www.careercup.com/question?id=5186833205952512
 *
 * @author alisovenko 04.10.14
 */
public class LongestSubarrayWithPositiveNumbers {
    public static int[] findLargestSubarray(int[] array) {
        int curSum = 0, curStart = -1;
        int maxSum = 0, maxStart = -1, maxEnd = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if (curStart < 0) {
                    // We only came to next sequence of positive numbers, lets' store the start idx (inclusive)
                    curStart = i;
                }
                curSum += array[i];
                if (curSum > maxSum) {
                    // update max indices
                    maxSum = curSum;
                    maxStart = curStart;
                }
            } else {
                if (curStart >= 0 && maxStart == curStart) {
                    // we just left the maximum sequence, let's remember ending index (exclusive)
                    maxEnd = i;

                }
                // reset fields
                curSum = 0;
                curStart = -1;
            }
        }
        // Special case for last element (don't forget about tail!)
        if (array[array.length - 1] > 0 && curStart >= 0 && maxStart == curStart) {
            maxEnd = array.length;
        }
        if (maxStart < 0) {
            return new int[]{};
        }
        return Arrays.copyOfRange(array, maxStart, maxEnd);
    }

    public static void main(String[] args) {
        int[] largestSubarray = findLargestSubarray(new int[]{2, 5, 1, -1, -2, 8, 9});
        assert Arrays.equals(largestSubarray, new int[]{8, 9}) : Arrays.toString(largestSubarray);
        int[] largestSubarray1 = findLargestSubarray(new int[]{-1, -3, -6, -2, -9});
        assert Arrays.equals(largestSubarray1, new int[]{}) : Arrays.toString(largestSubarray);
        int[] largestSubarray2 = findLargestSubarray(new int[]{-9, 3, 2, 3, -4, 4, 3});
        assert Arrays.equals(largestSubarray2, new int[]{3, 2, 3}) : Arrays.toString(largestSubarray);
    }
}

package com.alisovenko.coderust.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko 20.12.14
 */
public class MergeOverlappingIntervals {
    public static List<Integer[]> merge(List<Integer[]> intervals) {
        if (intervals.size() == 0) {
            return Collections.emptyList();
        }
        Integer[] lastInterval = intervals.get(0);
        List<Integer[]> result = new ArrayList<>();

        for (int i = 1; i < intervals.size(); i++) {
            Integer[] curInterval = intervals.get(i);
            if (lastInterval[1] >= curInterval[0]) {
                // Found overlapping interval - merging the last
                lastInterval[1] = Math.max(lastInterval[1], curInterval[1]);
            }
            else {
                // Just found new non-overlapping interval
                result.add(lastInterval);
                lastInterval = curInterval;
            }
        }
        result.add(lastInterval);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(merge(Arrays.asList(new Integer[]{0, 2}, new Integer[]{1, 4}, new Integer[]{3, 5}, new Integer[]{6, 11}, new Integer[]{7, 8}, new Integer[]{9, 10})));
    }
}

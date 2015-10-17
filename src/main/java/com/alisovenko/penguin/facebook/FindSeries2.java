package com.alisovenko.penguin.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko 11.07.15
 */
public class FindSeries2 {
    public static List<Integer> findSeries(int level, List<Integer> arr) {
        if (level <= 0 || arr == null || arr.size() == 0) {
            return Collections.emptyList();
        }
        List<Integer> interim = new ArrayList<>();

        for (int i = 0; i < level; i++) {
            populate(arr, interim);
            arr = interim;
            interim = new ArrayList<>();
        }
        return arr;
    }
    private static void populate(List<Integer> source, List<Integer> sink) {
        if (source.size() == 0) {
            return;
        }
        int runningTotal = 1;
        int lastInt = source.get(0);

        for (int i = 1; i < source.size(); i++) {
            if (source.get(i) != lastInt) {
                sink.add(runningTotal);
                sink.add(lastInt);
                runningTotal = 0;
            }
            lastInt = source.get(i);
            runningTotal++;
        }

        // tail
        sink.add(runningTotal);
        sink.add(lastInt);
    }

    public static void main(String[] args) {
        System.out.println(findSeries(2, Arrays.asList(1, 1)));
        System.out.println(findSeries(1, Arrays.asList(1, 6, 6)));
        System.out.println(findSeries(3, Arrays.asList(1, 2, 3)));
    }
}

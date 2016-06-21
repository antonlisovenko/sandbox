package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/1/16.
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return Collections.emptyList();

        SortedMap<Integer, List<Interval>> alignedIntervals = new TreeMap<>();

        for (Interval i: intervals) {
            addInterval(alignedIntervals, i.start, i);
        }

        int currentStart = alignedIntervals.keySet().iterator().next();
        int currentMaxEnding = Integer.MIN_VALUE;
        List<Interval> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Interval>> entry: alignedIntervals.entrySet()) {
            if (currentMaxEnding != Integer.MIN_VALUE && currentMaxEnding < entry.getKey()) {
                result.add(new Interval(currentStart, currentMaxEnding));
                currentStart = entry.getKey();
            }

            for (Interval interval: entry.getValue()) {
                currentMaxEnding = Math.max(currentMaxEnding, interval.end);
            }
        }
        // Tail
        result.add(new Interval(currentStart, currentMaxEnding));

        return result;
    }
    private void addInterval(SortedMap<Integer, List<Interval>> map, int pos, Interval i) {
        map.putIfAbsent(pos, new ArrayList<>());
        map.get(pos).add(i);
    }
    private static class Interval {
             int start;
             int end;
             Interval() { start = 0; end = 0; }
             Interval(int s, int e) { start = s; end = e; }
         }
}

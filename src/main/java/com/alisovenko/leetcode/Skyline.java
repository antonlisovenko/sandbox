package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         1/15/16.
 */
public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) return Collections.emptyList();

        List<int[]> result = new ArrayList<>();
        NavigableMap<Integer, List<Building>> flow = buildFlow(buildings);
        TreeSet<Building> sortedBuildings = new TreeSet<>(Comparator.reverseOrder());

        int curHeight = 0;

        for (Integer x: flow.keySet()) {
            List<Building> curBuildings = flow.get(x);
            for (Building b: curBuildings) {
                if (sortedBuildings.contains(b)) {
                    sortedBuildings.remove(b);
                } else {
                    sortedBuildings.add(b);
                }
                int newHeight = sortedBuildings.size() > 0 ? sortedBuildings.iterator().next().height : 0;
                if (curHeight != newHeight) {
                    curHeight = newHeight;
                    result.add(new int[]{x, newHeight});
                }
            }
        }

        return result;
    }

    private NavigableMap<Integer, List<Building>> buildFlow(int[][] buildings) {
        NavigableMap<Integer, List<Building>> result = new TreeMap<>();

        for (int[] b: buildings) {
            Building building = new Building(b[2]);
            add(result, b[0], building);
            add(result, b[1], building);
        }
        return result;
    }

    private void add(NavigableMap<Integer, List<Building>> map, int x, Building b) {
        map.putIfAbsent(x, new ArrayList<>());
        map.get(x).add(b);
    }

    private static class Building implements Comparable<Building>{
        public final int height;

        public Building(int height) {
            this.height = height;
        }

        public int compareTo(Building other) {
            int res = Integer.compare(height, other.height);
            if (res == 0) {
                return Integer.compare(hashCode(), other.hashCode());
            }
            return res;
        }
    }
}

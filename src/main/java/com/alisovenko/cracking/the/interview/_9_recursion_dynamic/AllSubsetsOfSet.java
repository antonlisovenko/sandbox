package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import java.util.*;

/**
 * @author alisovenko 15.10.14
 */
public class AllSubsetsOfSet {
    public static List<List<Integer>> find(List<Integer> set, int n) {
        if (n < 0) {
            ArrayList<List<Integer>> objects = new ArrayList<>();
            objects.add(new ArrayList<>());
            return objects;
        }
        List<List<Integer>> result = find(set, n - 1);
        List<List<Integer>> clones = new ArrayList<>();

        for (final List<Integer> integers : result) {
            List<Integer> clone = append(integers, set.get(n));
            clones.add(clone);
        }
        result.addAll(clones);
        return result;
    }

    private static List<Integer> append(List<Integer> integers, Integer i) {
        List<Integer> clone = new ArrayList<>(integers);
        clone.add(i);
        return clone;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = find(Arrays.asList(3, 6, 8, 1), 3);

        lists.forEach(l -> System.out.println(l));
    }
}

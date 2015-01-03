package com.alisovenko.penguin.facebook;

import com.alisovenko.base.Utils;
import com.google.common.base.Joiner;

import java.util.*;

/**
 * @author alisovenko 19.10.14
 */
public class FindKSubsets {
    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        Utils.startFrame(String.format("[%d] %s", idx, print(current)));

        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));

            Utils.endFrame(String.format("success [%d]", idx));
            return;
        }
        //unsuccessful stop clause
        if (idx == superSet.size()) return;

        Integer x = superSet.get(idx);
        current.add(x);

        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
        current.remove(x);

        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution);

        Utils.endFrame(String.format("return [%d]", idx));
    }

    private static String print(Set<Integer> current) {
        return Joiner.on(',').join(current);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<>(), res);
        return res;
    }

    public static void main(String[] args) {
        List<Set<Integer>> subsets = getSubsets(Arrays.asList(1, 2, 3, 4, 5), 3);

        for (final Set<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }


}

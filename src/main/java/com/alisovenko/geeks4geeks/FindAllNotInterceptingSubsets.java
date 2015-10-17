package com.alisovenko.geeks4geeks;

import java.util.*;

/**
 * @author alisovenko 03.04.15
 */
public class FindAllNotInterceptingSubsets {
    public static void findAllSubsets(int[] in, int idx, List<Integer> current, int curSum, TreeSet<Set<Integer>> result, int K) {
        // base case: if the sum is higher than K - we don't need to recurse further
        if (curSum > K) {
            return;
        }
        if (curSum <= K && current.size() > 0) {
            result.add(new HashSet<>(current));
        }
        for (int i = idx; i < in.length; i++) {
            current.add(in[i]);
            findAllSubsets(in, i + 1, current, curSum + in[i], result, K);
            current.remove(current.size() - 1);
        }
    }

    public static void findAndFilter(int[] in, int K) {
        TreeSet<Set<Integer>> result = new TreeSet<>((o1, o2) -> {
            int compare = Integer.compare(o2.size(), o1.size());
            return compare == 0 ?  - 1 : compare;
        });
        findAllSubsets(in, 0, new ArrayList<>(), 0, result, K);

        boolean found = false;
        List<Set<Integer>> filtered = new ArrayList<>();
        for (Set<Integer> set : result) {
            for (final Set<Integer> integers : filtered) {
                if (integers.containsAll(set)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                filtered.add(set);
            }
            found = false;
        }

        for (final Set<Integer> set : filtered) {
            System.out.println(set);
        }
    }

    public static void main(String[] args) {
        findAndFilter(new int[]{1, 2, 3, 4, 5}, 7);
    }

}

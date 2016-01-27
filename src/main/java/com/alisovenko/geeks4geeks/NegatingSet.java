package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko
 *         1/21/16.
 */
public class NegatingSet {
    public static List<Integer> findNegatingSubset(List<Integer> set) {
        return findRecursively(set, new ArrayList<>(), 0, set.stream().mapToInt(Integer::intValue).sum(), 0);
    }

    private static List<Integer> findRecursively(List<Integer> set, List<Integer> negativeSet, int start, int positiveSumm, int negativeSumm) {
        if (positiveSumm + negativeSumm == 0) {
            return negativeSet;
        }

        for (int i = start; i < set.size(); i++) {
            int n = set.get(i);
            negativeSet.add(n);

            List<Integer> res = findRecursively(set, negativeSet, i + 1, positiveSumm - n, negativeSumm - n);
            if (res != null) return res;

            negativeSet.remove(negativeSet.size() - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(NegatingSet.findNegatingSubset(new ArrayList<>()));
        System.out.println(NegatingSet.findNegatingSubset(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(NegatingSet.findNegatingSubset(Arrays.asList(1, 1, 1, 1, 1, 1)));
        System.out.println(NegatingSet.findNegatingSubset(Arrays.asList(10, 2, 3, 1, 2, 2)));

        System.out.println(NegatingSet.findNegatingSubset(Arrays.asList(1, 1, 1, 1, 1, -3)));
        System.out.println(NegatingSet.findNegatingSubset(Arrays.asList(1, 2, 3)));
    }
}
package com.alisovenko.penguin.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * in:  4, 2
 * out: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
 * @author alisovenko 18.10.14
 */
public class AllSubsetsOfKSize {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        List<List<Integer>> result  = new ArrayList<>();
        process(numbers, new HashSet<>(), 0, k, result);
        return result;
    }

    private void process(List<Integer> superList, Set<Integer> current, int idx, int k, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (idx == superList.size()) {
            return;
        }

        Integer s = superList.get(idx);
        current.add(s);

        process(superList, current, idx + 1, k, result);

        current.remove(s);

        process(superList, current, idx + 1, k, result);
    }

    public static void main(String[] args) {
        System.out.println(new AllSubsetsOfKSize().combine(4, 2));
    }
}

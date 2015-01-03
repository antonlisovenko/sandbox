package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntPermutations {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> suffix = new ArrayList<>();
        for (int i : num) {
            suffix.add(i);
        }
        permute(new ArrayList<>(), suffix, result);
        return result;
    }

    public void permute(List<Integer> prefix, List<Integer> suffix, List<List<Integer>> result) {
        if (suffix.size() == 0) {
            result.add(new ArrayList<>(prefix));
            return;
        }
        for (int i = 0; i < suffix.size(); i++) {
            // First add the int to prefix
            int s = suffix.get(i);
            prefix.add(s);

            // remove it from suffix
            suffix.remove(i);

            // recurse
            permute(prefix, suffix, result);

            // return the int to suffix
            suffix.add(i, s);

            // remove back the int from prefix
            prefix.remove(prefix.size() - 1);
        }
    }

    public static void main(String[] args) {
        new IntPermutations().permute(new int[]{1});
    }
}

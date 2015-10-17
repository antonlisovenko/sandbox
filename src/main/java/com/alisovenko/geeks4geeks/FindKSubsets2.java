package com.alisovenko.geeks4geeks;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 02.04.15
 */
public class FindKSubsets2 {
    public void findAllKSubsets(int[] data, List<Integer> current, int k, int idx) {
        if (k == current.size()) {
            System.out.println(current);
            return;
        }
        for (int i = idx; i < data.length - (k - current.size() - 1); i++) {
            current.add(data[i]);
            findAllKSubsets(data, current, k, i + 1);
            current.remove(current.size() - 1);
        }
    }
    public void find (int[] data, int k) {
        findAllKSubsets(data, new ArrayList<>(), k, 0);
    }

    public static void main(String[] args) {
        new FindKSubsets2().find(new int[]{1, 2, 3, 4, 5}, 2);
        new FindKSubsets2().find(new int[]{1, 2, 3, 4, 5}, 3);
    }
}

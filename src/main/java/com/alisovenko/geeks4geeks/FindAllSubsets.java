package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko 03.04.15
 */
public class FindAllSubsets {
    public static void findAllSubsets(int[] in, int idx, List<Integer> current) {
        if (current.size() > 0) {
            System.out.println(current);
        }
        for (int i = idx; i < in.length; i++) {
            current.add(in[i]);
            findAllSubsets(in, i + 1, current);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        findAllSubsets(new int[]{1, 2, 3, 4, 5}, 0, new ArrayList<>());
    }
}

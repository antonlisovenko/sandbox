package com.alisovenko.geeks4geeks;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author alisovenko 08.01.15
 */
public class SortOneArrayAsAnother {
    public static Integer[] sortByRelativeOrder(int[] first, int[] second) {
        HashMap<Integer, Integer> int2Pos = new HashMap<>();

        for (int i = 0; i < second.length; i++) {
            int2Pos.put(second[i], i);
        }

        Integer[] fCopy = new Integer[first.length];
        for (int i = 0; i < first.length; i++) {
            fCopy[i] = first[i];
        }
        Arrays.sort(fCopy, (f, s) -> int2Pos.get(f) == null || int2Pos.get(s) == null ? 1 : Integer.compare(int2Pos.get(f), int2Pos.get(s)));

        return fCopy;
    }

    public static void main(String[] args) {
        int[] first = {5, 2, 7, 8, 6, 9, 1, 2, 6};
        int[] second = {6, 2, 7, 8, 5};

        System.out.println(Arrays.toString(sortByRelativeOrder(first, second)));
    }
}

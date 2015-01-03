package com.alisovenko.cracking.the.interview._2_linked_lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * The other way - recursive (O(n) for memory).
 * @author alisovenko 17.08.14
 */
public class _2_2 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList(Arrays.asList(3, 2, 5, 2, 5, 7, 8, 4, 9));
        assert findK(list, 4) == 7;
    }
    private static int findK(LinkedList<Integer> list, int k) {
        int pos = 0;
        int num = 0;
        Iterator<Integer> it = list.iterator();
        for (Integer next : list) {
            pos++;

            if (pos >= k) {
                num = it.next();
            }
        }

        return num;
    }
}

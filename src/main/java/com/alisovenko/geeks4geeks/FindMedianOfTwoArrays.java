package com.alisovenko.geeks4geeks;

import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 08.01.15
 */
public class FindMedianOfTwoArrays {
    public static int findMedian(List<Integer> first, List<Integer> second) {
        if (first.size() != second.size()) {
            throw new IllegalStateException();
        }
        if (first.size() == 1) {
            return (first.get(0) + second.get(0)) / 2;
        }
        if (first.size() == 2) {
            return (Math.max(first.get(0), second.get(0)) + Math.min(first.get(1), second.get(1))) / 2;
        }
        int f = median(first);
        int s = median(second);
        int n = first.size();

        if (f == s) {
            // found the median - it is the same for both sublists
            return f;
        }

        if (f <= s) {
            if ((first.size() & 1) == 0) {
                return findMedian(first.subList(n / 2 - 1, n), second.subList(0, n / 2 + 1));
            }
            return findMedian(first.subList(n / 2, n), second.subList(0, n / 2 + 1));
        }
        else {
            if ((first.size() & 1) == 0) {
                return findMedian(first.subList(0, n / 2 + 1), second.subList(n / 2 - 1, n));
            }
            return findMedian(first.subList(0, n / 2 + 1), second.subList(n / 2, n));
        }
    }

    private static int median (List<Integer> list) {
        int n = list.size();
        if ((list.size() & 1) == 0) {
            return (list.get(n / 2 - 1) + list.get(n / 2)) / 2;
        }
        return list.get(n / 2);
    }

    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1, 2, 3, 6);
        List<Integer> l2 = Arrays.asList(4, 6, 8, 10);

        System.out.println(findMedian(l, l2));
    }
}

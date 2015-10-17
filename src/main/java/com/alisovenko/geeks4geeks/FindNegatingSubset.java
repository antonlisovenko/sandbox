package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 12.01.15
 */
public class FindNegatingSubset {
    public static List<Integer> findNegatingSubset(List<Integer> head, List<Integer> tail, int headSum, int tailSum) {
        if (tail.size() == 0) {
            // base case 1: we exhausted the tail, but have not found the negating subset
            return null;
        }
        if (tailSum + headSum == 0) {
            // base case 2: we found the necessary subset
            return head;
        }


        for (int i = 0; i < tail.size(); i++) {
            int p = tail.get(i);
            int newHeadSum = headSum - p;
            int newTailSum = tailSum - p;

            // take the element from tail and put it into head
            head.add(-p);

            // new tail - without the element
            List<Integer> newTail = new ArrayList<>(tail.size() - 1);
            if (i < tail.size() - 1) {
                if (i != 0) {
                    newTail.addAll(tail.subList(0, i));
                }
                newTail.addAll(tail.subList(i + 1, tail.size()));
            } else {
                newTail.addAll(tail.subList(0, tail.size() - 1));
            }
            List<Integer> result = findNegatingSubset(head, newTail, newHeadSum, newTailSum);
            if (result != null) {
                // base case 3: if we found the subset somewhere below - just forwarding the result upwards
                return result;
            }

            // not found - let's remove the element from head
            if (head.size() > 0) {
                head.remove(head.size() - 1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Integer> in1 = Arrays.asList(1, 2, 5, 7, 2, 9);
        List<Integer> in2 = Arrays.asList(5, 8, 5, 10, 2, 4);
        System.out.println(findNegatingSubset(new ArrayList<>(), in1, 0, in1.stream().mapToInt(Integer::intValue).sum()));
        System.out.println(findNegatingSubset(new ArrayList<>(), in2, 0, in2.stream().mapToInt(Integer::intValue).sum()));
    }
}

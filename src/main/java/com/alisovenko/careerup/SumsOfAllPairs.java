package com.alisovenko.careerup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.careercup.com/question?id=5652354158297088
 *
 * @author alisovenko 11.10.14
 */
public class SumsOfAllPairs {
    public static void findPairs(int[] input) {
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                int sum = input[i] + input[j];

                List<Integer> positions = pairs.get(sum);
                if (pairs.get(sum) == null) {
                    positions = new ArrayList<>();
                }
                for (int k = 0; k < positions.size(); k += 2) {
                    System.out.printf("(%d, %d, %d, %d)", i, j, k, k + 1);
                }
                positions.addAll(Arrays.asList(i, j));
            }
        }
    }

    public static void main(String[] args) {
        findPairs(new int[] {3,4,7,1,2,9,8});
    }
}

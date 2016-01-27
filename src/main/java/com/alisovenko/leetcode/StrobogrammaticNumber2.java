package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko
 *         1/17/16.
 */
public class StrobogrammaticNumber2 {
    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) return Collections.emptyList();
        if (n == 1) return Arrays.asList("0", "1", "8");

        // All permutations of set [0, 1, 6, 8, 9] of length n/2 concatenating the reverse version (with exclusions for 6 and 9)
        List<String> result = new ArrayList<>();
        findAllPermutations(result, 0, n, new ArrayList<>());

        return result;
    }

    private void findAllPermutations(List<String> result, int k, int n, List<Character> current) {
        // Base case: we can dump the current sequence
        if (k == (n + 1) / 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < current.size(); i++) sb.append(current.get(i));
            for (int i = n/2 - 1; i >= 0; i--) sb.append(current.get(i) == '9' ? '6' : (current.get(i) == '6' ? '9' : current.get(i)));

            result.add(sb.toString());
            return;
        }
        if (k != 0) {
            current.add('0');
            findAllPermutations(result, k + 1, n, current);
            current.remove(current.size() - 1);
        }

        current.add('1');
        findAllPermutations(result, k + 1, n, current);
        current.remove(current.size() - 1);

        current.add('8');
        findAllPermutations(result, k + 1, n, current);
        current.remove(current.size() - 1);

        // Edge case: we shouldn't add '6' and '9' as the middle element for odd-length numbers
        if (n % 2 == 0 || k != n / 2) {
            current.add('6');
            findAllPermutations(result, k + 1, n, current);
            current.remove(current.size() - 1);

            current.add('9');
            findAllPermutations(result, k + 1, n, current);
            current.remove(current.size() - 1);
        }
    }
}

package com.alisovenko.careerup;

import java.util.*;
import java.util.stream.IntStream;

/**
 * http://www.careercup.com/question?id=5637944224251904
 *
 * Given a number M (N-digit integer) and K-swap operations(a swap
 * operation can swap 2 digits), devise an algorithm to get the maximum possible integer?
 * Examples:
 * M = 132 K = 1 output = 312
 * M = 132 K = 2 output = 321
 * M = 7899 k = 2 output = 9987
 * M = 8799 and K = 2 output = 9987
 * @author alisovenko 06.10.14
 */
public class LargestNumberKSwaps2 {
    public static void kSwap(List<Integer> input, int k) {
        if (k == 0 || input.size() <= k) {
            return;
        }

        // Finding the maximum digit in the list
        int m = getMaxDigit(input, k);

        // This contains all indexes in the array, that == m
        List<Integer> maxPositions = new ArrayList<>();

        // This contains all values from 0 to maxPosition.size()
        List<Integer> forSwapping = new ArrayList<>();

        int leftCursor = 0;
        // We iterate two cursors - one from the end that gathers indeces of all values == m
        // and the other from the head - just increment. Loop stops when cursors meet
        for (int i = input.size() - 1; i > 0 && i > leftCursor; i--) {
            if (input.get(i) == m) {
                forSwapping.add(input.get(leftCursor));
                maxPositions.add(i);
                leftCursor++;
            }
        }
        int len = maxPositions.size();

        // The most important part - we sort the 'head' in ascending order
        // to move in place of maximum elements in correct order
        Collections.sort(forSwapping);

        // Now just swap max item elements with #len first ones
        for (int i = 0; i < len; i++) {
            input.set(i, m);
            input.set(maxPositions.get(i), forSwapping.get(i));
        }

        // Recurse for sublist (#len first elements are already inplace)
        kSwap(input.subList(len, input.size()), k - len);
    }

    private static int getMaxDigit(List<Integer> input, int k) {
        return input.stream().max(Comparator.<Integer>naturalOrder()).get();
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 3, 2));
        int k = 1;
        kSwap(l, k);
        System.out.println(l);

        l = new ArrayList<>(Arrays.asList(1, 3, 2));
        k = 2;
        kSwap(l, k);
        System.out.println(l);

        l = new ArrayList<>(Arrays.asList(7, 8, 9, 9));
        k = 2;
        kSwap(l, k);
        System.out.println(l);

        l = new ArrayList<>(Arrays.asList(8, 7, 9, 9));
        k = 2;
        kSwap(l, k);
        System.out.println(l);

        l = new ArrayList<>(Arrays.asList(8, 7, 6, 9, 5, 9));
        k = 3;
        kSwap(l, k);
        System.out.println(l); // This is not correct :((

    }


































/*





    public static List<Integer> swap2max(List<Integer> s, int K) {
        // the recursion end:
        if (s.size() == 0 || K == 0)
            return s;

        int m = getMaxDigit(s);

        // an array of indices of the maxdigits in the string
        List<Integer> indices = new ArrayList<>();

        // a counter for the length of that array, to determine how many chars
        // from the front will be swapped
        int len = 0;

        // an array of digits to be swapped
        List<Integer> front = new ArrayList<>();

        // and the index of the last of those:
        int right = 0;

        // get those indices, in a loop with 2 conditions:
        // * just run backwards through the string, until we meet the swapped range
        // * no more swaps then left (K)
        for (int i = s.size() - 1; i > right && len < K; i--) {
            if (m == s.get(i)) {
                indices.add(i);
                len++;
                // omit digits that are already in the right place
                while (s.get(right)== m)
                    right++;
                // and add the next one
                front.add(s.get(++right));
            }
        }

        // sort the digits to swap
        Collections.sort(front);

        // and swap them
        for (int i = 0; i < len; i++)
            s.set(indices.get(i), front.get(i));

        // the first len digits are the max ones
        // the rest the result of calling the function on the rest of the string
        return Coll + swap2max(string.substr(len), K - len)
    }*/

}

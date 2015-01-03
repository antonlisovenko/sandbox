package com.alisovenko.careerup;

import java.util.*;

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
public class LargestNumberKSwaps3 {
    public static void swapToMax(int[] arr, int k) {
        if (k >= arr.length - 1) {
            // counting sort by descending order, it takes O(n)
            sortDesc(arr, 0, arr.length - 1);
            return;
        }
        int[] exsit = new int[10];
        for (int i = 0; i < arr.length; i++) {
            exsit[arr[i]]++;
        }
        int index = exsit.length - 1;
        int swapIndex = 0;
        while (k > 0 && index > 0 && swapIndex < arr.length) {
            if (exsit[index] > 0) {
                // numbers of elements to swap
                int dis = Math.min(k, exsit[index]);
                for (int i = swapIndex; i < swapIndex + dis; i++) {
                    if (arr[i] == index) {
                        // skip the element which should not be swapped
                        exsit[index]--;
                    }
                }
                sortDesc(arr, swapIndex, swapIndex + dis - 1);
                for (int i = swapIndex + dis; i < arr.length; i++) {
                    if (k == 0 || exsit[index] == 0) {
                        break;
                    }
                    if (arr[i] == index) {
                        while (arr[swapIndex] == index) {
                            swapIndex++;
                        }
                        swap(arr, swapIndex, i);
                        k--;
                        exsit[index]--;
                        swapIndex++;
                    }
                }
            }
            index--;
        }
    }
    private static void swap(int[] digits, int from, int to) {
        int t = digits[from];
        digits[from] = digits[to];
        digits[to] = t;
    }

    private static void sortDesc(int[] arr, int from, int to) {
        to += 1;
        int[] temp = new int[10];
        int[] aux = new int[to - from];

        for (int i = from; i < to; i++) {
            temp[arr[i] + 1]++;
        }
        for (int i = 0; i < temp.length - 1; i++) {
            temp[i + 1] += temp[i];
        }
        for (int i = from; i < to; i++) {
            aux[temp[arr[i]]++] = arr[i];
        }
        for (int i = 0; i < aux.length; i++) {
            arr[i + from] = aux[i];
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 3, 2));
        int k = 1;
        int[] arr = {1, 3, 2};
        swapToMax(arr, k);
        System.out.println(Arrays.toString(arr));

        k = 2;
        arr = new int[]{1, 3, 2};
        swapToMax(arr, k);
        System.out.println(Arrays.toString(arr));

        k = 2;
        arr = new int[]{7,8,9,9};
        swapToMax(arr, k);
        System.out.println(Arrays.toString(arr));

        k = 2;
        arr = new int[]{8,7,9,9};
        swapToMax(arr, k);
        System.out.println(Arrays.toString(arr));

        k = 2;
        arr = new int[]{8, 7, 6, 9, 5, 9};
        swapToMax(arr, k);
        System.out.println(Arrays.toString(arr));

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

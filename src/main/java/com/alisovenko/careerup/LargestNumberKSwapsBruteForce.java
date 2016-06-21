package com.alisovenko.careerup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class LargestNumberKSwapsBruteForce {
    static int max = 0;

    public static void swapToMax(int[] arr, int k, int start) {
        if (k == 0) {
            int n = 0;
            for (int i : arr) {
                n *= 10;
                n += i;
            }
            max = Math.max(n, max);
            return;
        }
        for (int i = start + 1; i < arr.length; i++) {
            swap(arr, start, i);
            swapToMax(arr, k - 1, start + 1);
            swap(arr, start, i);
        }
    }

    public static int find(int[] arr, int k) {
        max = 0;
        swapToMax(arr, k, 0);
        return max;
    }

    private static void swap(int[] digits, int from, int to) {
        int t = digits[from];
        digits[from] = digits[to];
        digits[to] = t;
    }

    public static void main(String[] args) {
        /*List<Integer> l = new ArrayList<>(Arrays.asList(1, 3, 2));
        int k = 1;
        int[] arr = {1, 3, 2};
        swapToMax(arr, k, 0);
        System.out.println(max);

        k = 2;
        arr = new int[]{1, 3, 2};
        swapToMax(arr, k, 0);
        System.out.println(max);*/

        System.out.println(find(new int[]{7, 8, 9, 9}, 2));
        System.out.println(find(new int[]{8, 7, 9, 9}, 2));
        System.out.println(find(new int[]{8, 7, 6, 9, 5, 9}, 2));

    }

    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        String[] levels = new String[]{"", "Thousand", "Million", "Billion"};
        int l = 0;

        List<String> res = new ArrayList<>();
        while (num > 0) {
            List<String> ans = new ArrayList<>();
            int m = num % 10;
            num /= 10;
            int p = num % 10;

            // if num == 0 then single mapping
            if (num == 0 || p != 1) {
                map(m, ans);
            }
            if (num > 0) {
                switch (p * 10 + m) {
                    case 10: ans.add("Ten"); break;
                    case 11: ans.add("Eleven"); break;
                    case 12: ans.add("Twelve"); break;
                    case 13: ans.add("Thirteen"); break;
                    case 14: ans.add("Fourteen"); break;
                    case 15: ans.add("Fifteen"); break;
                    case 16: ans.add("Sixteen"); break;
                    case 17: ans.add("Seventeen"); break;
                    case 18: ans.add("Eighteen"); break;
                    case 19: ans.add("Nineteen"); break;
                    default: {
                        switch (p) {
                            case 2: ans.add("Twenty"); break;
                            case 3: ans.add("Thirty"); break;
                            case 4: ans.add("Forty"); break;
                            case 5: ans.add("Fifty"); break;
                            case 6: ans.add("Sixty"); break;
                            case 7: ans.add("Seventy"); break;
                            case 8: ans.add("Eighty"); break;
                            case 9: ans.add("Ninety"); break;
                        }
                    }
                }
            }
            num /= 10;
            if (num > 0) {
                // Third radix
                m = num % 10;
                if (m > 0 ) ans.add("Hundred");
                map(m, ans);
            }
            num /= 10;

            String level = levels[l++];
            if (ans.size() > 0) res.add(level);
            res.addAll(ans);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 1; i >= 0; i--) sb.append(res.get(i)).append(" ");

        return sb.toString().trim();
    }
    private void map(int i, List<String> ans) {
        switch (i) {
            case 1: ans.add("One"); break;
            case 2: ans.add("Two"); break;
            case 3: ans.add("Three"); break;
            case 4: ans.add("Four"); break;
            case 5: ans.add("Five"); break;
            case 6: ans.add("Six"); break;
            case 7: ans.add("Seven"); break;
            case 8: ans.add("Eight"); break;
            case 9: ans.add("Nine"); break;
        }
    }
}
package com.alisovenko.interviews.google;

import java.util.Arrays;

/**
 * @author alisovenko 15.02.15
 */
public class CalculateRetirement {

    public static void find4HighestYears(int[] salaries, int PERIOD, int NUMBER) {
        int[] summs = new int[salaries.length - PERIOD + 1];

        // calculate the year from the first month
        for (int i = 0; i < PERIOD; i++) {
            summs[0] += salaries[i];
        }

        // calculate years from the other months
        for (int i = 1; i < salaries.length - PERIOD + 1; i++) {
            summs[i] = summs[i - 1] - salaries[i - 1] + salaries[i + PERIOD - 1];
        }


        // all horizontal values are not ascending
        // cache[i][j] - the most year revenue in case i month is included
        int cache[][] = new int[NUMBER][summs.length];

        // filling the first line: for one year - it's simple - just find the maximum byt the i month
        cache[0][0] = summs[0];
        for (int i = 1; i < summs.length; i++) {
            cache[0][i] =  Math.max(cache[0][ i - 1], summs[i]);
        }

        // 1 offset for years
        for (int i = 1; i < NUMBER; i++) {
            for (int j = 0; j < summs.length; j++) {
                if (j >= PERIOD) {
                    cache[i][j] = Math.max(cache[i - 1][j - PERIOD] + summs[j], cache[i][j - 1]);
                }
                else {
                    // just copy previous (btw, these cells will be equal for all "rows")
                    cache[i][j] = cache[i - 1][j];
                }
            }
        }

        printArray("summs", summs);
        System.out.println("cache");
        for (final int[] ints : cache) {
            System.out.println(Arrays.toString(ints));
        }

        int maxIdx = summs.length - 1;
        int t = cache[cache.length - 1][summs.length - 1];
        // years pos appoints to the first month in the year (+NUMBER months)
        int[] months = new int[NUMBER];
        int p = NUMBER - 1;

        for (int i = cache.length - 1; i >= 0; i--) {
            for (int j = maxIdx; j > 0; j--) {
                if (cache[i][j] != t) {
                    months[p--] = j + 1;
                    maxIdx = j - PERIOD + 1;
                    t = i > 0 ? cache[i - 1][maxIdx] : 0;
                    break;
                }
            }
        }

        printArray("months", months);
    }

    private static void printArray(String name, int[] array) {
        System.out.printf("%s:\n%s\n", name, Arrays.toString(array));
    }

    public static void main(String[] args) {
        find4HighestYears(new int[]{1, 1, 1, 5, 2, 6, 8, 9, 1, 1, 4}, 3, 2);
        find4HighestYears(new int[]{1, 1, 1, 5, 2, 6, 8, 9, 1, 1, 4, 10, 1}, 3, 3);
        find4HighestYears(new int[]{1, 1, 1, 5, 2, 0, 0, 6, 8, 9, 1, 1, 4, 10, 1}, 3, 3);
        find4HighestYears(new int[]{1, 1, 1, 5, 2, 0, 20, 6, 8, 9, 1, 1, 4, 10, 1}, 3, 3);
    }
}

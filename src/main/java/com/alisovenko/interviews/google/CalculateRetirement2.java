package com.alisovenko.interviews.google;

import java.util.Arrays;

/**
 * @author alisovenko 15.02.15
 */
public class CalculateRetirement2 {
    public static final int PERIOD = 4;

    public static void find4HighestYears(int[] salaries) {
        // array of year revenues starting from the  12 month
        int[] summs = new int[salaries.length - PERIOD];

        // calculate the year from the first month
        for (int i = 0; i < 12; i++) {
            summs[0] += salaries[i];
        }

        // calculate years from the other months
        for (int i = 1; i < salaries.length - PERIOD; i++) {
            summs[i] = summs[i - 1] - salaries[i - 1] + salaries[i + PERIOD];
        }

        // all horizontal values are not ascending
        // cache[i][j] - the most year revenue in case i month is included
        int cache[][] = new int[4][summs.length + 1];

        // filling the first line: for one year - it's simple - just find the maximum byt the i month
        cache[0][0] = summs[0];
        for (int i = 1; i < summs.length; i++) {
            cache[0][i] =  Math.max(cache[0][ i - 1], summs[i]);
        }

        // 1 offset for years
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j <= summs.length; j++) {
                if (j > PERIOD) {
                    cache[i][j] = Math.max(cache[i - 1][j - PERIOD] + summs[j - 1], cache[i][j - 1]);
                    if (cache[i][j] != cache[i][j - 1]) {
                        System.out.printf("new max: j = %d (%d)", j - 1, summs[j - 1]);
                    }
                }
                else {
                    // just copy previous
                    cache[i][j] = cache[i - 1][j];
                }
            }
        }

        int maxIdx = summs.length - 1;
        int t = cache[3][summs.length - 1];
        int[] yearsPos = new int[4];
        int p = 3;

        for (int i = cache.length - 1; i > 0; i--) {
            for (int j = maxIdx; j > 0; j--) {
                if (cache[i][j] != t) {
                    yearsPos[p--] = maxIdx;
                    maxIdx = j - PERIOD + 1;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(yearsPos));
    }

    public static void main(String[] args) {
        find4HighestYears(new int[]{40, 10, 70, 30, 7, 9, 11, 0, 0, 12, 5, 1, 4, 8, 3, 2, 15, 16, 20, 1, 1, 7, 9});
        find4HighestYears(new int[]{0, 0, 7, 7, 7, 1, 1, 9, 9, 11, 3, 3, 1, 7, 10, 1, 10, 2});
        find4HighestYears(new int[]{10, 2, 5, 7, 2, 8, 0, 2, 6, 1, 7, 9, 2});
    }
}

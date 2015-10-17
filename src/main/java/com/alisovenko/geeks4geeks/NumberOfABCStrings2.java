package com.alisovenko.geeks4geeks;

import com.google.common.base.Stopwatch;

import java.util.Arrays;

/**
 * @author alisovenko 05.02.15
 */
public class NumberOfABCStrings2 {
    private static int count(int n, boolean hasB, boolean hasC, int i, char[] current) {
        if (n == 0) {
            System.out.println(Arrays.toString(current));
            return 1;
        }
        int c1 = 0, c2 = 0, c3 = 0;
        current[i] = 'a';
        c1 = count(n - 1, hasB, hasC, i + 1, current);

        if (!hasB) {
            current[i] = 'b';
            c2 = count(n - 1, true, hasC, i + 1, current);
        }
        if (!(hasC && current[i - 1] == 'c')) {
            current[i] = 'c';
            if (i > 0 && current[i - 1] == 'c') {
                c3 = count(n - 1, hasB, true, i + 1, current);
            }
            else {
                c3 = count(n - 1, hasB, false, i + 1, current);
            }
        }
        return c1 + c2 + c3;
    }

    public static void main(String[] args) {
        int n = 20;
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(count(n, false, false, 0, new char[n]));
        System.out.println(stopwatch.stop());

        stopwatch.reset().start();

        System.out.println(NumberOfABCStrings.findSequences(n).size());
        System.out.println(stopwatch.stop());

    }
}

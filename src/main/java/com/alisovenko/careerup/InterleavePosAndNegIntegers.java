package com.alisovenko.careerup;

import java.util.Arrays;

/**
 * http://www.careercup.com/question?id=5183920823861248 Sure, the best way is to use additional structure (array,
 * queue, etc), but there is way to do this with O(1) additional space and O(n) runtime. The trick is to keep 4
 * pointers: positive, swap positive, negative, swap negative and one cursor. Cursor moves through the array, at each
 * iteration the following invariant is provided: positive and negative pointers point to next positive and negative
 * numbers in the array. "Swap" pointers are used in cases, when we need to swap the next integer (e.g. expected +, but
 * have -, so we swap - to next + and appoint swap negative variable to just moved position). At each iteration swap
 * variable keeps the position of next positive or negative number, that must be placed at next matching cursor
 * position.
 *
 * @author alisovenko 21.09.14
 */
public class InterleavePosAndNegIntegers {
    static int dp, sp, dn, sn;
    static int cursor = 0;
    static int[] data;

    public static void interleave() {

        boolean expectedPositive, tail = false;
        int N = data.length;

        // initialization
        dp = -1;
        sp = -1;
        dn = -1;
        sn = -1;
        cursor = 0;
        expectedPositive = data[0] > 0;
        propagatePositive(false);
        propagateNegative(false);

        while (cursor < N) {
            printStats("main" + cursor);
            boolean nextPositive = data[cursor] > 0;

            // These are good situations: numbers are placed ok - just skipping
            if ((tail || expectedPositive) && nextPositive) {
                propagatePositive(true);
            } else if ((tail || !expectedPositive) && !nextPositive) {
                propagateNegative(true);
            }
            // Bad situation1:
            else if (expectedPositive && !nextPositive) {
                swapNegativeWithPositive();
            } else if (!expectedPositive && nextPositive) {
                swapPositiveWithNegative();
            }

            // if positive or negative numbers ended - just propagate the tail
            if (dp < 0 || dn < 0) {
                tail = true;
            }
            expectedPositive = !expectedPositive;
            cursor++;
        }
    }

    private static void swapNegativeWithPositive() {
        assert dn == cursor;
        // two swaps: current negative with swapped negative, and then current negative with future positive
        if (sn > dn) {
            swap(data, cursor, sn);
            printStats("neg->pos.0");
        }

        swap(data, cursor, dp);

        // point swap negative pointer to just moved negative number
        sn = dp;

        printStats("neg->pos.1");

        // propagate
        propagatePositive(false);
        propagateNegative(false);

        printStats("neg->pos.2");
    }

    private static void swapPositiveWithNegative() {
        assert dp == cursor;
        // two swaps: current positive with swapped positive, and then current positive with future negative
        if (sp > dp) {
            swap(data, cursor, sp);
            printStats("pos->neg.0");
        }
        swap(data, cursor, dn);

        // point swap negative pointer to just moved negative number
        sp = dn;

        printStats("pos->neg.1");

        // propagate
        propagateNegative(false);
        propagatePositive(false);

        printStats("pos->neg.2");
    }

    private static void propagatePositive(boolean swap) {
        //        assert dp == cursor;
        if (sp > dp && swap) {
            swap(data, dp, sp);
        }
        for (int i = dp + 1; i < data.length; i++) {
            if (data[i] > 0) {
                dp = i;
                return;
            }
        }
        // there is no more positive
        dp = -1;
    }

    private static void propagateNegative(boolean swap) {
        //        assert dn == cursor;
        if (sn > dn && swap) {
            swap(data, dn, sn);
        }
        for (int i = dn + 1; i < data.length; i++) {
            if (data[i] < 0) {
                dn = i;
                return;
            }
        }
        // there is no more negative
        dn = -1;
    }

    private static void printStats(String str) {
        System.out.printf("[%s] dn: %d, sn: %d, dp: %d, sp: %d, array: %s\n", str, dn, sn, dp, sp,
                Arrays.toString(data));
    }

    private static void swap(int[] ints, int i, int j) {
        int t = ints[i];
        ints[i] = ints[j];
        ints[j] = t;
    }

    public static void main(String[] args) {
        check(new int[]{-1, -8, -5, -6, 7, 9, -3, 1, 6}, new int[]{-1, 7, -8, 9, -5, 1, -6, 6, -3});
        // with tail
        check(new int[]{2, 3, -5, 6, 7}, new int[]{2, -5, 3, 6, 7});
        // unchanged
        check(new int[]{-1, 2, -3, 4, -5, 6, -7, 8}, new int[]{-1, 2, -3, 4, -5, 6, -7, 8});

        check(new int[]{1, 5, -2, 3, 7, -3, 6, 6, -2}, new int[]{1, -2, 5, -3, 3, -2, 7, 6, 6});
    }

    private static void check(int[] input, int[] expected) {
        data = input;
        interleave();
        assert Arrays.equals(data, expected) : String.format("Expected: %s\n Result  : %s\n", Arrays.toString(expected),
                Arrays.toString(data));
        System.out.println("        OK: " + Arrays.toString(data));
    }
}

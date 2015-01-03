package com.alisovenko.algorithms.sort;

import org.junit.Assert;

import org.junit.Test;

import java.util.Arrays;

/**
 * Base class for sort algorithms
 * 
 * <p>
 * Created: 11.10.2010
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public abstract class BaseSortTest {
    static Integer[] array = { 4, 2, 33, 6, 42, 13, 16, 6, 1, 11, 32, 16, 1, 4 };

    static Integer[] expected;

    static Integer[] array2;

    static Integer[] expected2;

    static {
        expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        array2 = new Integer[1000000];
        for (int i = 0; i < 1000000; i++) {
            array2[i] = Integer.valueOf((int) (100 * Math.random()));
        }

        expected2 = Arrays.copyOf(array2, array2.length);
        Arrays.sort(expected2);
    }

    @Test
    public void testAlgorithm() {
        sort(array);

        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void testPerformanceOfAlgorithm() {
        long time = System.currentTimeMillis();
        sort(array2);

        long newTime = System.currentTimeMillis() - time;

        System.out.println(">>>>> Time for sorting 10000000 records: " + (newTime) + " milliseconds");

        Assert.assertArrayEquals(expected2, array2);
    }

    public abstract void sort(Integer[] array);

    static void swap(Integer[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}

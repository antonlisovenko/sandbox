package com.alisovenko.algorithms.streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author alisovenko
 *         5/25/16.
 */
public class StreamsPractice {
    public static void main(String[] args) {
        int[] ints = IntStream.range(0, 1000).toArray();

        Arrays.stream(ints).limit(5).forEach(System.out::println);
        System.out.println();
        Arrays.stream(ints).sorted().parallel().limit(5).forEach(System.out::println);
        System.out.println();

        Arrays.stream(ints).unordered().limit(5).forEach(System.out::println);
        System.out.println();
        Arrays.stream(ints).parallel().unordered().limit(5).forEach(System.out::println);

    }
}

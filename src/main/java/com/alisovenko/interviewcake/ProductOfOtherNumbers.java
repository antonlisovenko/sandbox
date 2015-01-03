package com.alisovenko.interviewcake;

import java.util.Arrays;

/**
 * https://www.interviewcake.com/question/product-of-other-numbers
 *
 * You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
 Write a function get_products_of_all_ints_except_at_index() that takes an array of integers and returns an array of the products.
 For example, given:
 [1, 7, 3, 4]
 your function would return:
 [84, 12, 28, 21]
 by calculating:
 [7*3*4, 1*3*4, 1*7*4, 1*7*3]
 Do not use division in your solution.
 * @author alisovenko 21.11.14
 */
public class ProductOfOtherNumbers {
    public static int[] products(int[] numbers) {
        int[] left = new int[numbers.length];
        int[] right = new int[numbers.length];

        int floating = 1;
        for (int i = 0; i < numbers.length; i++) {
            left[i]  = i > 0 ? (floating *= numbers[i - 1]) : 1;
        }

        floating = 1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            right[i]  = i < numbers.length - 1 ? (floating *= numbers[i + 1]) : 1;
        }

        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(products(new int[]{1, 2, 6, 5, 9})));
    }
}

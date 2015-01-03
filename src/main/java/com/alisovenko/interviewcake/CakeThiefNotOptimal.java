package com.alisovenko.interviewcake;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://www.interviewcake.com/question/cake-thief
 * You are a renowned thief who has recently switched from stealing precious metals to stealing cakes because of the insane profit margins. You end up hitting the jackpot, breaking into the world's largest privately owned stock of cakes—the vault of the Queen of England.
 While Queen Elizabeth has a limited number of types of cake, she has an unlimited supply of each type.

 Each type of cake has a weight and a value, stored in tuples ↴ with two positions:

 An integer representing the weight of the cake in kilograms
 An integer representing the monetary value of the cake in British pounds

 For example:
 # weighs 7 kilograms and has a value of 160 pounds
 (7, 160)

 # weighs 3 kilograms and has a value of 90 pounds
 (3, 90)

 You brought a duffel bag that can hold limited weight, and you want to make off with the most valuable haul possible.
 Write a function max_duffel_bag_value() that takes an array of cake tuples and a weight capacity, and returns the maximum monetary value the duffel bag can hold.
 For example:
 cake_tuples = [(7, 160), (3, 90), (2, 15)]
 capacity    = 20

 max_duffel_bag_value(cake_tuples, capacity)
 # returns 555 (6 of the middle type of cake and 1 of the last type of cake)

 * @author alisovenko 21.11.14
 */
public class CakeThiefNotOptimal {
    public static int maxDuffelBagValue(int[][] tuples, int capacity) {
        Arrays.sort(tuples, (o1, o2) -> {
            int[] first = o1;
            int[] second = o2;

            double r1 = 0;
            double r2 = 0;
            if (first[0] > 0 && first[1] > 0) {
                r1 = (double)first[1]/first[0];
            }
            if (second[0] > 0 && second[1] > 0) {
                r2 = (double)second[1]/second[0];
            }

            return r1 > r1 ? 1 : ((r1 < r2) ? -1 : 0);
        });

        int result = 0;
        for (int i = tuples.length - 1; i >= 0 && capacity > 0; i--) {
            int weight = tuples[i][0];
            int value = tuples[i][1];
            if (weight == 0 || value == 0) {
                continue;
            }

            if (weight <= capacity) {
                int num = capacity / weight;
                capacity %=weight;
                result += num * value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] tuples = {{7, 160}, {3, 90}, {2, 15}, {0,0}};
        System.out.println(maxDuffelBagValue(tuples, 20));
    }
}

package com.alisovenko.interviewcake;

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
public class UnboundedKnapsackCakeThiefDynamic {
    public static int maxDuffelBagValue(int[][] tuples, int capacity) {
        int[] maxCache = new int[capacity + 1];

        for (int i = 1; i <= capacity; i++) {
            int curMax = 0;

            for (int[] tuple : tuples) {
                int currentWeight = tuple[0];
                int currentValue = tuple[1];

                if (currentWeight <= i) {
                    int value = currentValue + maxCache[i - currentWeight];
                    curMax = Math.max(value, curMax);
                }
            }
            maxCache[i] = curMax;
        }

        return maxCache[capacity];
    }

    public static void main(String[] args) {
        int[][] tuples = {{7, 160}, {3, 90}, {2, 15}, {0,0}};
        System.out.println(maxDuffelBagValue(tuples, 20));
    }
}

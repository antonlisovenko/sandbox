package com.alisovenko.penguin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author alisovenko 21.09.14
 */
public class GooglePhoneRequestsGeneration {
    public static void generate(int... weights) {
        assert IntStream.of(weights).sum() == 100;

//        Arrays.sort(weights);
        int[] runningWeights = new int[weights.length];

        for (int i = 0; i < weights.length; i++) {
            if (i > 0) {
                runningWeights[i] = weights[i] + runningWeights[i - 1];
            }
            else {
                runningWeights[i] = weights[i];
            }
        }

        Random random = new Random();
        int[] res = new int[3];
        for (int i = 0; i < 100; i++) {
            int n = random.nextInt(100);
            for (int j = 0; j < runningWeights.length; j++) {
                if (n < runningWeights[j]) {
                    res[j]++;
                    System.out.println(j);
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }

    public static void main(String[] args) {
        generate(40, 10, 50);
    }
}

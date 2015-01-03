package com.alisovenko.coderust.arrays;

import java.util.Arrays;

/**
 * @author alisovenko 20.12.14
 */
public class MoveZerosToLeft {
    public static int[] move(int[] array) {
        int p = array.length -1;

        for (int i = array.length - 1; i >=0; i--) {
            if (array[i] > 0) {
                array[p--] = array[i];
            }
        }

        for (int i = p; i >= 0; i--) {
            array[i] = 0;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(move(new int[]{0, 2, 5, 2, 0, 0, 4, 6, 9, 0, 5, 0})));
        System.out.println(Arrays.toString(move(new int[]{1,1, 2, 3, 4,6 ,7})));
        System.out.println(Arrays.toString(move(new int[]{0, 0, 0, 0, 0})));
    }
}

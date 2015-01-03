package com.alisovenko.arithmetics.binary;

/**
 * User: alisovenko Date: 21.12.12
 */
public class BitsMirror {
    public static void main(String[] args) {
        int p = 45252;
        String binary = Integer.toBinaryString(p);
        System.out.println(binary);

        int r = 0;
        int pointer = 0;
        int size = binary.length();

        for (int i = 0; i < size; i++) {
            r |= (p&pointer) << size - i;
            pointer <<= 1;
        }

        System.out.println(Integer.toBinaryString(r));

    }
}

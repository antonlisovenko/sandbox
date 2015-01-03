package com.alisovenko.careerup;

/**
 * @author alisovenko 24.09.14
 */
public class ReverseDigits {
    public static int reverse(int i) {
        int r = 0;
        while (i != 0) {
            r *= 10;
            r += i % 10;
            i /= 10;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(reverse(35340));
        System.out.println(reverse(732));
    }
}

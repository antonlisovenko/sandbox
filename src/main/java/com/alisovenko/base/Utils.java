package com.alisovenko.base;

import java.util.List;

/**
 * @author alisovenko 16.12.14
 */
public class Utils {

    private static int c= 0;

    public static void startFrame(String txt) {
        c++;
        pad();
        System.out.println(txt);
    }

    public static void endFrame(String txt) {
        c--;
        pad();
        System.out.println(txt);
    }
    private static void pad() {
        for (int i = 0; i < c; i++) {
            System.out.print('.');
        }
        System.out.print(" ");
    }
}

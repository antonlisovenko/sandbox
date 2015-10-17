package com.alisovenko.indeed;

import java.util.Scanner;

/**
 * @author alisovenko 10.01.15
 */
public class Practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        String s = scanner.next();

        System.out.println(String.valueOf(a + b + c) + " " + s);
    }
}

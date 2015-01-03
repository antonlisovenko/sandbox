package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko 13.12.14
 */
public class AllPairsOfBrackets {
    private static int c= 0;
    private static void recursive(int n, int leftCnt, int rightCnt, List<Character> output) {
        startFrame(leftCnt, rightCnt, output);

        if (leftCnt >= n && rightCnt >= n) {
//            printList(output);
        }
        if (leftCnt < n) {
            output.add('(');
            recursive(n, leftCnt + 1, rightCnt, output);
            output.remove(output.size() - 1);
        }
        if (rightCnt < leftCnt) {
            output.add(')');
            recursive(n, leftCnt, rightCnt + 1, output);
            output.remove(output.size() - 1);
        }

        endFrame(leftCnt, rightCnt);
    }

    private static void endFrame(int leftCnt, int rightCnt) {
        c--;
        pad();
        System.out.printf(" < [%d, %d] ", leftCnt, rightCnt);
        System.out.println();
//        System.out.println("return [" + c + "]");
    }

    private static void startFrame(int leftCnt, int rightCnt, List<Character> output) {
        pad();
        System.out.printf(" > [%d, %d] ", leftCnt, rightCnt);
        printList(output);
        System.out.println();
//        System.out.println(" [" + c + "]");
        c++;
    }

    private static void pad() {
        for (int i = 0; i < c; i++) {
            System.out.print('.');
        }
    }

    private static void printList(List<Character> output) {
        for (final Character ch : output) {
            System.out.print(ch);
        }
//        System.out.println();
    }

    public static void main(String[] args) {
        recursive(3, 0, 0, new ArrayList<>());

    }
}

package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko 09.01.15
 */
public class ReplaceQuestionMarkAmazon {

    public static void printSimpleRegexp(String str) {
        doPrint(str, 0);
    }

    private static void doPrint(String str, int pos) {
        // base case: no more asterix found - can print the string
        int nextAsterix = findAsterix(str, pos);
        if (nextAsterix < 0) {
            System.out.println(str);
            return;
        }

        doPrint(replace(str, nextAsterix, '1'), nextAsterix + 1);
        doPrint(replace(str, nextAsterix, '0'), nextAsterix + 1);
    }

    private static int findAsterix(String str, int pos) {
        if (pos < str.length()) {
            for (int i = pos; i < str.length(); i++) {
                if (str.charAt(i) == '?') {
                    return i;
                }
            }
        }
        return -1;
    }

    private static String replace(String str, int pos, char c) {
        return str.substring(0, pos) + c + str.substring(pos + 1);
    }


    public static void main(String[] args) {
        printSimpleRegexp("Ama??zzzzz?on");
    }
}

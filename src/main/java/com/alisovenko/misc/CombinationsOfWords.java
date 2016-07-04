package com.alisovenko.misc;

/**
 * @author alisovenko
 *         7/3/16.
 */
public class CombinationsOfWords {
    public static void print(String[][] strings) {
        permute(strings, 0, "");
    }

    public static void permute(String[][] strings, int idx, String s) {
        if (idx >= strings.length) {
            System.out.println(s);
            return;
        }
        for (int i = 0; i < strings[idx].length; i++) {
            permute(strings, idx + 1, s + " " + strings[idx][i]);
        }
    }

    public static void main(String[] args) {
        print(new String[][] { {"quick", "slow"}, {"brown", "red"}, {"fox", "dog"}});
    }

}

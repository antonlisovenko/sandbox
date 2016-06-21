package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/10/16.
 */
public class FlipGame {
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] arr = s.toCharArray();

        return canWinHelper(arr);
    }

    private boolean canWinHelper(char[] arr) {
        int i = 0;

        for (i = 0; i < arr.length - 1; i++) {
            if (arr[i] == '+' && arr[i + 1] == '+') {
                arr[i] = '-';
                arr[i + 1] = '-';

                boolean win = !canWinHelper(arr);

                arr[i] = '+';
                arr[i + 1] = '+';

                if (win) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new FlipGame().canWin("++++"));
        System.out.println(new FlipGame().canWin("+++++"));
        System.out.println(new FlipGame().canWin("++++++"));
        System.out.println(new FlipGame().canWin("+++++++"));
        System.out.println(new FlipGame().canWin("++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("+++++++++++++++++++++++++"));
        System.out.println(new FlipGame().canWin("++++++++++++++++++++++++++"));
    }
}

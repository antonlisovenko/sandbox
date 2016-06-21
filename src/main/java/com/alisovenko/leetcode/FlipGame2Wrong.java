package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/10/16.
 */
public class FlipGame2Wrong {
    public boolean canWin(String s) {
        // cache keeping the information about whether the starter guaranteed to win for '+' of length i
        int[] cache = new int[s.length() + 1];

        boolean wins = false;

        int start = -1;

        for (int i = 0; i < s.length(); i++) {
            if (start < 0 && s.charAt(i) == '+') {
                // found new start of range
                start = i;
            } else if (i == '-' && start >= 0 && i - start > 1) {
                // found new range,
                wins = wins ^ starterWins(cache, i - start);
                start = -1;
            }
        }
        // tail
        if (start >= 0) wins = wins ^ starterWins(cache, s.length() - start);

        return wins;
    }

    private boolean starterWins(int[] cache, int n) {
        if (n < 2) return false;
        if (cache[n] != 0) return cache[n] > 0;

        for (int i = 0; i < n - 1; i++) {
            // If in the left middle and right middle sings are the same - then starter wins
            if (starterWins(cache, i) == starterWins(cache, n - i - 2)) {
                cache[n] = 1;
                System.out.printf("%s: true (i: %s)\n", n, i);
                return true;
            }
        }
        System.out.printf("%s: false\n", n);
        cache[n] = -1;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new FlipGame2Wrong().canWin("+++++++++"));
    }
}

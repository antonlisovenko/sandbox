package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 13.03.15
 */
public class MinPalyndromesInString {
    public static int findMinPalyndromes(String s) {
        // mp[i][j] indicates the number of palyndromes inside the diapazone
        int[][] mp = new int[s.length()][s.length()];

        for (int i = 0; i < mp.length; i++) {
            mp[i][i] = 1;
        }

        // range length (increasing by one)
        for (int i = 1; i < mp.length; i++) {
            for (int p = 0; p + i < mp.length; p++) {
                // s[p..t] is the observed substring
                int t = p + i;

                if (s.charAt(p) == s.charAt(t) && (t - p == 1 ||  mp[p + 1][t - 1] == 1)) {
                    mp[p][t] = 1;
                }
                else {
                    int min = Integer.MAX_VALUE;
                    for (int v = p; v < t; v++) {
                        min = Math.min(mp[p][v] + mp[v + 1][t], min);
                    }
                    mp[p][t] = min;
                }
            }
        }
        return mp[0][mp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(findMinPalyndromes("aabaacc"));
        System.out.println(findMinPalyndromes("abcdegf"));
        System.out.println(findMinPalyndromes("aaaaaaaaaaaa"));
        System.out.println(findMinPalyndromes("aabbaabb"));
    }
}

package com.alisovenko.coderust;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alisovenko 20.10.14
 */
public class Boogle {
    public static List<String> findAllWords(char[][] matrix, Set<String> dictionary) {
        List<String> result = new ArrayList<>();

        for (final String s : dictionary) {
            // for all characters, that start with s.charAt(0)
            for (int[] pos : allWordsStartWith(s.charAt(0), matrix)) {
                String w = find(matrix, pos[0], pos[1], s, 0, new boolean[matrix.length * matrix.length]);
                if (w != null) {
                    result.add(w);
                }
            }
        }
        return result;
    }

    private static String find(char[][] matrix, int x, int y, String w, int n, boolean[] visited) {
        if (n == w.length() - 1) {
            // got it!
            return w;
        }
        int N = matrix.length;
        char c = w.charAt(++n);

        // scan through all neighbours looking for next character
        for (int i = Math.max(0, x - 1); i < Math.min(N, x + 2); i++) {
            for (int p = Math.max(0, y - 1); p < Math.min(N, y + 2); p++) {
                // Skipping itself
                if (!(i == x && p == y) && !visited[N * y + x]) {
                    if (matrix[i][p] == c) {
                        visited[N * y + x] = true;

                        String res = find(matrix, i, p, w, n, visited);
                        if (res != null) {
                            // no need in further iterations if we found
                            return res;
                        }
                        visited[N * y + x] = false;
                    }
                }
            }
        }
        return null;
    }

    private static List<int[]> allWordsStartWith(char c, char[][] matrix) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int p = 0; p < matrix[i].length; p++) {
                if (matrix[i][p] == c) {
                    result.add(new int[]{i, p});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                findAllWords(new char[][]{{'c', 'a', 't'}, {'r', 'r', 'e'}, {'t', 'o', 'n'}},
                        ImmutableSet.of("cat", "cater", "art", "toon", "moon", "not", "eat", "ton")));
    }

}

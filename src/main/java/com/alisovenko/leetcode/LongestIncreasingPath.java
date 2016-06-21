package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         2/4/16.
 */
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;

        // Cache contains the already calculated maxLength of increasing path from [i][j] point
        int[][] cache = new int[N][M];

        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            for (int p = 0; p < M; p++) {
                maxLength = Math.max(maxLength, dfs(matrix, cache, i, p, null));
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int[][] cache, int i, int p, Integer valFrom) {
        if (i < 0 || i == matrix.length || p < 0 || p == matrix[0].length
                || (valFrom != null && valFrom >= matrix[i][p])) return 0;

        if (cache[i][p] > 0) return cache[i][p];

        // DFS further
        int c = matrix[i][p];
        cache[i][p] = Math.max(
                Math.max(dfs(matrix, cache, i - 1, p, c),
                        dfs(matrix, cache, i + 1, p, c)),
                Math.max(dfs(matrix, cache, i, p - 1, c),
                        dfs(matrix, cache, i, p + 1, c))
        ) + 1;

        return cache[i][p];
    }
}

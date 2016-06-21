package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         4/3/16.
 */
public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length;
        int m = matrix[0].length;

        boolean clearFirstRow = false, clearFirstCol = false;
        for (int i = 0; i < n; i++)
            if (matrix[i][0] == 0) {
                clearFirstCol = true;
                break;
            }
        for (int i = 0; i < m; i++)
            if (matrix[0][i] == 0) {
                clearFirstRow = true;
                break;
            }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        if (clearFirstRow) for (int i = 0; i < m; i++) matrix[0][i] = 0;
        if (clearFirstCol) for (int i = 0; i < n; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0, 5}, {4, 3, 1, 4}, {0, 1, 1, 4}, {1, 2, 1, 3}, {0, 0, 1, 1}};
        new SetMatrixZeros().setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}

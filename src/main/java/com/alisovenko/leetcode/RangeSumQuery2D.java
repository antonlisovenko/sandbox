package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         3/31/16.
 */
public class RangeSumQuery2D {

    int[][] m;
    int[][] f;

    public RangeSumQuery2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        f = matrix;

        int n = matrix.length;
        int p = matrix[0].length;
        m = new int[n + 1][p + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= p; j++) {
                m[i][j] = m[i - 1][j] + m[i][j - 1] - m[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return m[row2 + 1][col2 + 1] - m[row1][col2 + 1] - m[row2 + 1][col1] + m[row1][col1];
    }

    public void update(int row, int col, int val) {
        long diff = val - f[row][col];
        if (diff == 0) return;
        // update all m[][] cells for new value
        for (int i = row + 1; i < m.length; i++) {
            for (int j = col + 1; j < m[0].length; j++) {
                m[i][j] += (int)diff;
            }
        }
        f[row][col] = val;
    }

    public static void main(String[] args) {
        RangeSumQuery2D r = new RangeSumQuery2D(new int[][]{{2, 4}, {-3, 5}});
        r.update(0,1,3);
        r.update(1,1,-3);
        r.update(0,1,1);
        System.out.println(r.sumRegion(0, 0, 1, 1));
    }
}

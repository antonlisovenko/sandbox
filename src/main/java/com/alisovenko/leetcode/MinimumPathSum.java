package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         10/17/15.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        // Moving from the last row to the first
        for (int i = grid.length - 1; i >= 0; i--) {
            // From the last column to the first
            for (int p = grid[0].length - 1; p >= 0; p--) {
                if (i == grid.length - 1 && p == grid[0].length - 1) {
                    // Just leave the value for right-bottom cell
                    continue;
                }
                int l = Integer.MAX_VALUE, v = Integer.MAX_VALUE;
                if (i < grid.length - 1) {
                    l = grid[i][p] + grid[i + 1][p];
                }
                if (p < grid[0].length - 1) {
                    v = grid[i][p] + grid[i][p + 1];
                }
                grid[i][p] = Math.min(l, v);
            }
        }

        return grid[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new MinimumPathSum().minPathSum(new int[][]{{1, 2}, {1, 1}}));
    }
}

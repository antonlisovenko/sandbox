package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         6/25/16.
 */
public class MrKMarsh2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt(), n = s.nextInt();
        boolean[][] grid = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            String line = s.next();
            for (int p = 0; p < n; p++) {
                grid[i][p] = line.charAt(p) == 'x' ? false : true;
            }
        }

        int[][] upMatrix = fillUp(grid, m, n);
        int[][] leftMatrix = fillLeft(grid, m, n);

        int max = find(grid, 0, 0, m - 1, n - 1, upMatrix, leftMatrix);
        if (max < 0) System.out.println("impossible");
        else System.out.println(max);
    }

    private static int find(boolean[][] grid, int r1, int c1, int r2, int c2, int[][] upMatrix, int[][] leftMatrix) {
        if (r1 >= r2 || c1 >= c2) return -1;
        int h = r2 - r1, w = c2 - c1;
        int perimeter = (h + w) << 1;
        boolean left = upMatrix[r2][c1] >= h;
        boolean right = upMatrix[r2][c2] >= h;
        boolean top = leftMatrix[r1][c2] >= w;
        boolean bottom = leftMatrix[r2][c2] >= w;

        if (left && right && top && bottom) return perimeter;

        int max = -1;
        if (!top) {
            max = Math.max(max, find(grid, r1 + 1, c1, r2, c2, upMatrix, leftMatrix));
        }
        if (!left && max < perimeter - 2) {
            max = Math.max(max, find(grid, r1, c1 + 1, r2, c2, upMatrix, leftMatrix));
        }
        if (!bottom && max < perimeter - 2) {
            max = Math.max(max, find(grid, r1, c1, r2 - 1, c2, upMatrix, leftMatrix));
        }
        if (!right && max < perimeter - 2) {
            max = Math.max(max, find(grid, r1, c1, r2, c2 - 1, upMatrix, leftMatrix));
        }
        return max;
    }

    private static int[][] fillUp(boolean[][] grid, int m, int n) {
        int[][] res = new int[m][n];
        // scanning cols
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int p = 0; p < m; p++) {
                if (!grid[p][i]) {
                    cnt = -1;
                }
                res[p][i] = cnt++;
            }
        }
        return res;
    }

    private static int[][] fillLeft(boolean[][] grid, int m, int n) {
        int[][] res = new int[m][n];
        // scanning rows
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int p = 0; p < n; p++) {
                if (!grid[i][p]) {
                    cnt = -1;
                }
                res[i][p] = cnt++;
            }
        }
        return res;
    }
}

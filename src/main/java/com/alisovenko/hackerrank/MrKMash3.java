package com.alisovenko.hackerrank;

import java.util.Scanner;

/**
 * @author alisovenko
 *         6/25/16.
 */
public class MrKMash3 {
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
        int vertical = Math.min(upMatrix[r2][c1], upMatrix[r2][c2]);
        int horizontal = Math.min(leftMatrix[r1][c2], leftMatrix[r2][c2]);

        if (vertical >= h && horizontal >= w) return perimeter;

        int max = -1;

        System.out.printf("vertical: %d, horizontal: %d, r1: %d, r2: %d, c1: %d, c2: %d\n", vertical, horizontal, r1, r2, c1, c2);

        if (vertical < h ) max = Math.max(max, find(grid, r1, c1, r2 - vertical - 2, c2, upMatrix, leftMatrix));
        if (vertical < h ) max = Math.max(max, find(grid, r2 - vertical, c1, r2, c2, upMatrix, leftMatrix));

        if (horizontal < w) max = Math.max(max, find(grid, r1, c1, r2, c2 - horizontal - 2, upMatrix, leftMatrix));
        if (horizontal < w) max = Math.max(max, find(grid, r2, c2 - horizontal, r2, c2, upMatrix, leftMatrix));

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

package com.alisovenko.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         6/25/16.
 */
public class MrKMarsh {
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

        boolean[][][] horizontal = fillHorizontal(grid, m, n);
        boolean[][][] vertical = fillVertical(grid, m, n);

        System.out.println(Arrays.deepToString(horizontal));
        System.out.println();
        System.out.println(Arrays.deepToString(vertical));

        int max = find(grid, 0, 0, m - 1, n - 1, horizontal, vertical);
        if (max < 0) System.out.println("impossible");
        else System.out.println(max);
    }

    private static int find(boolean[][] grid, int r1, int c1, int r2, int c2, boolean[][][] horizontal, boolean[][][] vertical) {
        if (r1 >= r2 || c1 >= c2) return -1;
        if (!horizontal[r1][c1][c2] && !horizontal[r2][c1][c2] && !vertical[c1][r1][r2] && !vertical[c2][r1][r2])
            return (r2 - r1 + c2 - c1) * 2;

        return Math.max(
                Math.max(find(grid, r1 + 1, c1, r2, c2, horizontal, vertical), find(grid, r1, c1 + 1, r2, c2, horizontal, vertical)),
                Math.max(find(grid, r1, c1, r2 - 1, c2, horizontal, vertical), find(grid, r1, c1, r2, c2 - 1, horizontal, vertical)));
    }

    private static boolean[][][] fillHorizontal(boolean[][] grid, int m, int n) {
        boolean[][][] res = new boolean[m][n][n];
        // scanning rows
        for (int i = 0; i < m; i++) {
            for (int p = 0; p < n; p++) {
                if (!grid[i][p]) {
                    for (int g = 0; g < n; g++) {
                        if (p < g) res[i][p][g] = true; // setting the whole line "from p"
                        if (g < p) res[i][g][p] = true; // setting the range from g to p
                    }
                }
            }
        }
        return res;
    }

    private static boolean[][][] fillVertical(boolean[][] grid, int m, int n) {
        boolean[][][] res = new boolean[n][m][m];
        // scanning cols
        for (int i = 0; i < n; i++) {
            for (int p = 0; p < m; p++) {
                if (!grid[p][i]) {
                    for (int g = 0; g < m; g++) {
                        if (p < g) res[i][p][g] = true; // setting the whole line "from p"
                        if (g < p) res[i][g][p] = true; // setting the range from g to p
                    }
                }
            }
        }
        return res;
    }
}

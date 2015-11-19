package com.alisovenko.leetcode;

import java.util.Arrays;

/**
 * @author alisovenko
 *         11/14/15.
 */
public class SurroundedRegionsRecursive {
    public void solve(char[][] board) {
        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board[0].length - 1; x++) {
                if (board[y][x] == '0') {
                    boolean needFill = check(y, x, board, new char[board.length][board[0].length]);
                    if (needFill) {
                        fill(y, x, board);
                    }
                }
            }
        }
    }
    private boolean check(int y, int x, char[][] board, char[][] visited) {
        char c = board[y][x];

        // 1. Base case - if '0' cell is on the border - finishing with false
        if (c == '0' && (y == 0 || y == board.length - 1 || x == 0 || x == board[0].length - 1)) {
            return false;
        }
        // 2. Base case 2 - if this is not a '0' or we have already visited this cell - return from recursion
        if (c == 'X' || visited[y][x] == 'X') {
            return true;
        }
        // Mark the cell as visited
        visited[y][x] = 'X';

        if (check(y - 1, x, board, visited) && check(y, x - 1, board, visited)
                && check(y + 1, x, board, visited) && check(y , x + 1, board, visited)) {
            return true;
        }
        return false;
    }
    private void fill(int y, int x, char[][] board) {
        // Base case - if this is 'x' or came to boundary
        if (board[y][x] == 'X' || y == 0 || y == board.length - 1 || x == 0 || x == board[0].length) {
            return;
        }
        // Mark the cell as 'X'
        board[y][x] = 'X';

        fill(y - 1, x, board);
        fill(y, x - 1, board);
        fill(y + 1, x, board);
        fill(y , x + 1, board);
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X'}, {'X', '0', 'X'}, {'X', 'X', 'X'}};
        new SurroundedRegionsRecursive().solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}

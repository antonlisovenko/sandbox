package com.alisovenko.leetcode;

import java.util.Map;
import java.util.Stack;

/**
 * @author alisovenko
 *         11/14/15.
 */
public class SurroundedRegionsIterative {
    public void solve(char[][] board) {
        for (int y = 1; y < board.length - 1; y++) {
            for (int x = 1; x < board[0].length - 1; x++) {
                if (board[y][x] == 'O') {
                    boolean needFill = check(y, x, board, new boolean[board.length][board[0].length]);
                    if (needFill) {
                        fill(y, x, board);
                    }
                }
            }
        }
    }
    private boolean check(int y, int x, char[][] board, boolean[][] visited) {
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        stack.push(new java.util.AbstractMap.SimpleEntry<>(y, x));
        visited[y][x] = true;

        Map.Entry<Integer, Integer> next;
        while (!stack.isEmpty()) {
            next = stack.peek();
            char c = board[next.getKey()][next.getValue()];
            int i = next.getKey();
            int p = next.getValue();
            // Base case - if 'O' cell is on the border - finishing with false
            if (c == 'O' && (i == 0 || i == board.length - 1 || p == 0 || p == board[0].length - 1)) {
                return false;
            }
            if (c == 'X') {
                stack.pop();
            }
            else {
                // Try neighbours
                if (!visited[i + 1][p]) add(stack, visited, i + 1, p);
                else if (!visited[i][p + 1]) add(stack, visited, i, p + 1);
                else if (!visited[i - 1][p]) add(stack, visited, i - 1, p);
                else if (!visited[i][p - 1]) add(stack, visited, i, p - 1);
                else stack.pop();
            }
        }

        return true;
    }

    private void add(Stack<Map.Entry<Integer, Integer>> stack, boolean[][] visited, int y, int x) {
        stack.push(new java.util.AbstractMap.SimpleEntry<>(y, x));
        visited[y][x] = true;
    }


    private void addAndMark(Stack<Map.Entry<Integer, Integer>> stack, char[][] board, int y, int x) {
        stack.push(new java.util.AbstractMap.SimpleEntry<>(y, x));
        board[y][x] = 'X';
    }
    private void fill(int y, int x, char[][] board) {
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        stack.push(new java.util.AbstractMap.SimpleEntry<>(y, x));

        Map.Entry<Integer, Integer> next;
        while (!stack.isEmpty()) {
            next = stack.peek();
            int i = next.getKey();
            int p = next.getValue();

            char c = board[i][p];

            if (c == 'X' || i == 0 || i == board.length - 1 || p == 0 || p == board[0].length) {
                stack.pop();
            }
            else {
                if (board[i + 1][p] != 'X') addAndMark(stack, board, i + 1, p);
                else if (board[i][p + 1] != 'X') addAndMark(stack, board, i, p + 1);
                else if (board[i - 1][p] != 'X') addAndMark(stack, board, i - 1, p);
                else if (board[i][p - 1] != 'X') addAndMark(stack, board, i, p - 1);
                else stack.pop();
            }
        }

    }
}

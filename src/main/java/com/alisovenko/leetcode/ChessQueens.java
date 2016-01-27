package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         12/18/15.
 */
public class ChessQueens {
    private static char[][] template;

    public List<List<String>> solveNQueens(int n) {
        initTemplate(n);

        List<List<String>> result = new ArrayList<>();
        solveRecursively(0, n, new int[n], result);

        return result;
    }

    private void solveRecursively(int l, int n, int[] board, List<List<String>> result) {
        // Base case1: we got to the level after last
        if (l >= n) {
            dumpBoard(board, result);
            return;
        }
        // Scanning level l
        for (int i = 0; i < n; i++) {
            // Base case2: no recursion if no unbeaten positions for the current level
            if (unbeaten(board, l, i)) {
                board[l] = i;
                solveRecursively(l + 1, n, board, result);
            }
        }
    }

    private boolean unbeaten(int[] board, int l, int pos) {
        int left = pos, right = pos, n = board.length - 1;
        for (int i = l - 1; i >= 0; i--) {
            int x = board[i];
            if (pos == x || (--left > 0 && x == left) || (++right <= n && x == right))
                return false;
        }
        return true;
    }

    private void dumpBoard(int[] board, List<List<String>> result) {
        List<String> markedBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            template[i][board[i]] = 'Q';
            markedBoard.add(new String(template[i]));
            template[i][board[i]] = '.';
        }
        result.add(markedBoard);
    }

    private void initTemplate(int n) {
        template = new char[n][];
        for(int i = 0; i < n; i++) {
            template[i] = new char[n];
            for (int p = 0; p < n; p++)
                template[i][p] = '.';
        }
    }
}

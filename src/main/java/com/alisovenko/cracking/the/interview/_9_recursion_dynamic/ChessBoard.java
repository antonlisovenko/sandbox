package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

/**
 * @author alisovenko 17.12.14
 */
public class ChessBoard {
    private static int findAllWays(int row, int[][] places) {
        // base case 1: we reached the end of board but have not found the way
        if (row >= 8) {
            return 1;
        }

        int result = 0;
        for (int i = 0; i < 8; i++) {
            if (check(places, row, i)) {
                // If the cell is "unbeaten" by anyone - we add it to route
                places[row][i] = 1;
                result += findAllWays(row + 1, places);

                // After recursion through all child routes we remove the current cell from routes and proceed
                places[row][i] = 0;
            }
        }
        return result;
    }

    private static boolean check(int[][] places, int row, int col) {
        for (int i = 0; i < row; i++) {
            int r[] = places[i];

            // checking the vertical beat
            if (r[col] > 0)
                return false;

            // checking left diagonal beat
            if (col >= row - i && r[col - (row - i)] > 0)
                return false;

            // checking right diagonal beat
            if (col <= 7 - (row - i) && r[col + (row - i)] > 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] places = new int[8][8];

        System.out.println(findAllWays(0, places));
    }
}

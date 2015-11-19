package com.alisovenko.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/18/15.
 */
public class GridSearch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\n\t ]+");
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            // Read the matrix
            int[][] matrix = readMatrix(in);

            // Read the sample matrix
            int[][] sample = readMatrix(in);

            if (search(matrix, sample)) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    private static boolean search(int[][] matrix, int[][] sample) {
        for (int i = 0; i < matrix.length - sample.length; i++) {
            for (int j = 0; j < matrix[0].length - sample[0].length; j++) {
                if (matrix[i][j] == sample[0][0] && candidateMatches(matrix, sample, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean candidateMatches(int[][] matrix, int[][] sample, int i, int j) {
        int p = j;
        for (int k = 0; k < sample.length; k++) {
            for (int l = 0; l < sample[0].length; l++) {
                if (matrix[i][j++] != sample[k][l]) {
                    return false;
                }
            }
            i++;
            j = p; // reverting the j variable
        }
        return true;
    }

    private static int[][] readMatrix(Scanner in) {
        int r = in.nextInt();
        int c = in.nextInt();

        int[][] result = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = in.next();
            result[i] = transformToNumbers(line);
        }
        return result;
    }

    private static int[] transformToNumbers(String line) {
        int[] res = new int[line.length()];

        char[] chars = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            res[i] = chars[i] - '0';
        }
        return res;
    }
}

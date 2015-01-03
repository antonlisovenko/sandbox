package com.alisovenko.algorithms.divideConquer;

import org.junit.Test;

/**
 * Performs the multiplication of two matrixes.
 *
 * <p>Created: 01.04.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class MatrixesMultiplication {
    @Test
    public void test() {
        int[][] first  = new int[][] {{2,1},
                                      {5,6}};
        int[][] second = new int[][] {{3,1},
                                      {1,2}};
        
        int[][] result = performStraightforwardly(first, second);
        prettyPrint(result);
    }
    
    private int[][] performStraightforwardly(int[][] first, int[][] second) {
        int result[][] = new int[first.length][second[0].length];
        int col = first[0].length;
        
        for (int i = 0; i < first.length; i++) {
            for (int p = 0; p < first[i].length; p++) {
                // We are working with first[i][p] element now
                int calculated = 0;
                for (int v = 0; v < col; v++) {
                    calculated += first[i][v] * second[v][p];
                }
                result[i][p] = calculated;
            }
        }
        
        return result;
    }
    
    private void prettyPrint(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int p = 0; p < input[i].length; p++) {
                System.out.print(input[i][p] + " ");
            }
            System.out.println();
        }
            
    }

}

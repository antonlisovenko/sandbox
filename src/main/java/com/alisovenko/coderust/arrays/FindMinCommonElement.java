package com.alisovenko.coderust.arrays;

/**
 * @author alisovenko 20.12.14
 */
public class FindMinCommonElement {
    public static int findMin(int[] first, int[] second, int[] third) {
        if (first.length == 0 && second.length == 0 && third.length == 0) {
            return -1;
        }
        int x = 0, y = 0, z = 0;

        while (x < first.length && y < second.length && z < third.length) {
            // If we found the common number - this is the least common number
            int nextX = first[x];
            int nextY = second[y];
            int nextZ = third[z];

            if (nextX == nextY && nextY == nextZ) {
                return nextX;
            }

            if (nextX <= nextY && nextX <= nextZ) {
                x++;
            }
            else if (nextY <= nextX && nextY <= nextZ) {
                y++;
            }
            else if (nextZ <= nextY && nextZ <= nextX) {
                z++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] first = {-5, -2, 0, 6, 8};
        int[] second = {-9, -1, 4, 8, 8};
        int[] third = {-20, -4, 1, 6, 9};
        System.out.println(findMin(first, second, third));
    }

}

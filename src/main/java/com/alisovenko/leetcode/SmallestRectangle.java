package com.alisovenko.leetcode;

/**
 * // This is wrong idea! Doesn't work
 *
 * @author alisovenko
 *         1/17/16.
 */
public class SmallestRectangle {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;

        int topcandidate = findTop(image, x, y);
        int rightcandidate = findRight(image, x, y);
        int bottomcandidate = findBottom(image, x, y);
        int leftcandidate = findLeft(image, x, y);

        System.out.printf("right: %s, left: %s, bottom: %s, top: %s\n", rightcandidate, leftcandidate, bottomcandidate, topcandidate);

        int top = scanTop(image, topcandidate);
        int right = scanRight(image, rightcandidate, top);
        int bottom = scanBottom(image, bottomcandidate, right);
        int left = scanLeft(image, leftcandidate, bottom, top);

        System.out.printf("right: %s, left: %s, bottom: %s, top: %s\n", right, left, bottom, top);
        return (right - left + 1) * (bottom - top + 1);
    }

    private int findTop(char[][] image, int x, int y) {
        while (x > 0 && image[x - 1][y] == '1') x--;
        return x;
    }

    private int findRight(char[][] image, int x, int y) {
        while (y < image[0].length - 1 && image[x][y + 1] == '1') y++;
        return y;
    }

    private int findBottom(char[][] image, int x, int y) {
        while (x < image.length - 1 && image[x + 1][y] == '1') x++;
        return x;
    }

    private int findLeft(char[][] image, int x, int y) {
        while (y > 0 && image[x][y - 1] == '1') y--;
        return y;
    }

    private int scanTop(char[][] image, int top) {
        for (int i = 0; i < image[0].length; i++) {
            if (top == 0) return top;

            while (top > 0 && image[top - 1][i] == '1') top--;
        }
        return top;
    }

    private int scanRight(char[][] image, int right, int top) {
        for (int i = top; i < image.length; i++) {
            if (right == image[0].length - 1) return right;

            while (right < image[0].length - 1 && image[i][right + 1] == '1') right++;
        }
        return right;
    }

    private int scanBottom(char[][] image, int bottom, int right) {
        for (int i = 0; i <= right; i++) {
            if (bottom == image.length - 1) return bottom;

            while (bottom < image.length - 1 && image[bottom + 1][i] == '1') bottom++;
        }
        return bottom;
    }

    private int scanLeft(char[][] image, int left, int bottom, int top) {
        for (int i = top; i <= bottom; i++) {
            if (left == 0) return left;

            while (left > 0 && image[i][left - 1] == '1') left--;
        }
        return left;
    }

    public static void main(String[] args) {
//        System.out.println(new SmallestRectangle().minArea(new char[][]{"0010".toCharArray(),"0110".toCharArray(),"0100".toCharArray()},0,2));
        System.out.println(new SmallestRectangle().minArea(new char[][]{
                "000000000000000000000010000000000000000000000000000000000000000000000000000".toCharArray(),
                "000000000000000000000110000000000000000000000000000000000000000000000000000".toCharArray(),
                "000000000000000000011100000000000000000000000000000000000000000000000000000".toCharArray(),
                "000000000000000000111100000000000000000000000000000000000000000000000000000".toCharArray(),
                "000000000000000100111000000000000010001000000000100000000000000000000000000".toCharArray(),
                "000000000000011111011000000000000011111100000011110000101000000000000000000".toCharArray(),
                "000000000000011011111100100000000011111110001111100111111100000000000000000".toCharArray(),
                "000000000000000001111111110000001111111111011110100111111110000000000000000".toCharArray(),
                "000000000000000000110011111110000111111100011111110101111100000000000000000".toCharArray(),
                "000000000000000000000111111111110111111111111111111111111111000000000000000".toCharArray(),
                "000000000000000000000111111111111111111111111111111111111111000000000000000".toCharArray(),
                "000000000000000000000111111111111000111000001111111111111011000000000000000".toCharArray(),
                "000000000000000000011111101111111000111111111110010111111111100000000000000".toCharArray(),
                "000000000000000000010111111111111111111111100000000111111111011000000000000".toCharArray(),
                "000000000000000000000111101100100111110000100011100111111111111000000000000".toCharArray(),
                "000000000000000000000111111111111111110001111111101111111111111100000000000".toCharArray(),
                "000000000000000000001111111111111111111111101111111111111111111100000000000".toCharArray(),
                "000000000000000000001000011111111000001000001111111111111111111100000000000".toCharArray()}, 17, 63));
//        System.out.println(new SmallestRectangle().minArea(new char[][]{"000".toCharArray(),"010".toCharArray(),"110".toCharArray()}, 1,1));
    }
}

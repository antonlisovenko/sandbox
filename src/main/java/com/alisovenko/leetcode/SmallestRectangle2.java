package com.alisovenko.leetcode;

/**
 * // This is wrong idea! Doesn't work
 *
 * @author alisovenko
 *         1/17/16.
 */
public class SmallestRectangle2 {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) return 0;

        int topcandidate = findTop(image, x, y);

        int[] extremums = scanContur(image, topcandidate, y);

        System.out.printf("right: %s, left: %s, bottom: %s, top: %s\n", extremums[1], extremums[3], extremums[2], extremums[0]);

        return (extremums[1] - extremums[3] + 1) * (extremums[2] - extremums[0] + 1);
    }

    private int[] scanContur(char[][] image, int topcandidate, int y) {
        int top = topcandidate, right = 0, bottom = 0, left = Integer.MAX_VALUE;
        int lastX = 0, lastY = 0;
        int curX = topcandidate, curY = y;

        do {
            int[] next = nextPoint(image, curX, curY, lastX, lastY);
            lastX = curX;
            lastY = curY;
            curX = next[0];
            curY = next[1];
            top = Math.min(top, lastX);
            right = Math.max(right, lastY);
            bottom = Math.max(bottom, lastX);
            left = Math.min(left, lastY);
        } while (!(curX == topcandidate && curY == y));

        return new int[]{top, right, bottom, left};
    }

    private int findTop(char[][] image, int x, int y) {
        while (x > 0 && image[x - 1][y] == '1') x--;
        return x;
    }

    private int[] nextPoint(char[][] image, int x, int y, int lastX, int lastY) {
        // start from the rightmost point
        if (!(lastX == x && lastY == y + 1) && isOne(image, x, y + 1) && (isZero(image, x - 1, y + 1) || isZero(image, x + 1, y + 1))) return new int[]{x, y + 1};
        if (!(lastX == x && lastY == y + 1) && isOne(image, x, y + 1) && (isZero(image, x - 1, y + 1) || isZero(image, x + 1, y + 1))) return new int[]{x, y + 1};
        if (!(lastX == x + 1 && lastY == y + 1) && isOne(image, x + 1, y + 1) && (isZero(image, x, y + 1) || isZero(image, x + 1, y))) return new int[]{x + 1, y + 1};
        if (!(lastX == x + 1 && lastY == y) && isOne(image, x + 1, y) && (isZero(image, x + 1, y + 1) || isZero(image, x + 1, y - 1))) return new int[]{x + 1, y};
        if (!(lastX == x + 1 && lastY == y - 1) && isOne(image, x + 1, y - 1) && (isZero(image, x , y - 1) || isZero(image, x + 1, y))) return new int[]{x + 1, y - 1};
        if (!(lastX == x && lastY == y - 1) && isOne(image, x, y - 1) && (isZero(image, x - 1, y - 1) || isZero(image, x + 1, y - 1))) return new int[]{x, y - 1};
        if (!(lastX == x - 1 && lastY == y - 1) && isOne(image, x - 1, y - 1) && (isZero(image, x - 1, y) || isZero(image, x, y - 1))) return new int[]{x - 1, y - 1};
        if (!(lastX == x - 1 && lastY == y) && isOne(image, x - 1, y) && (isZero(image, x - 1, y + 1) || isZero(image, x - 1, y - 1))) return new int[]{x - 1, y};
        if (!(lastX == x - 1 && lastY == y + 1) && isOne(image, x - 1, y + 1) && (isZero(image, x, y + 1) || isZero(image, x - 1, y - 1))) return new int[]{x - 1, y + 1};
        throw new RuntimeException();
    }

    private boolean isOne(char[][] image, int x, int y) {
        return x < image.length && y < image[0].length && image[x][y] == '1';
    }
    private boolean isZero(char[][] image, int x, int y) {
        return x == image.length || y == image[0].length || image[x][y] == '0';
    }


    public static void main(String[] args) {
        System.out.println(new SmallestRectangle2().minArea(new char[][]{"0010".toCharArray(),"0110".toCharArray(),"0100".toCharArray()},0,2));
        /*System.out.println(new SmallestRectangle2().minArea(new char[][]{
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
                "000000000000000000001000011111111000001000001111111111111111111100000000000".toCharArray()}, 17, 63));*/
//        System.out.println(new SmallestRectangle().minArea(new char[][]{"000".toCharArray(),"010".toCharArray(),"110".toCharArray()}, 1,1));
    }


}

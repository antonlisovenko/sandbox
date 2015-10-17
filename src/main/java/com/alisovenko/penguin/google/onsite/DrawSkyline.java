package com.alisovenko.penguin.google.onsite;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author alisovenko 05.01.15
 */
public class DrawSkyline {
    private static int height = 0;
    private static int curX = 0;

    private static void setHeight(int h) {height = h;}

    private static void drawTo(int x) {
        System.out.printf("H: %d, [%d - %d]\n", height, curX, x);
        curX = x;
    }
    public static void printSkyLine(int[][] x, int[] h) {
        Coordinate[] coordinates = transformToCoordinates(x, h);
        TreeSet<House> currentHouses = new TreeSet<>();

        for (int i = 0; i < coordinates.length - 1; i++) {
            Coordinate c = coordinates[i];
            Coordinate n = coordinates[i+1];

            if (c.opening) {
                currentHouses.add(c.house);
            }
            else {
                currentHouses.remove(c.house);
            }

            if (currentHouses.size() > 0) {
                House currentHighest = currentHouses.iterator().next();
                setHeight(currentHighest.height);
                drawTo(n.x);
            }
        }
    }

    static Coordinate[] transformToCoordinates(int[][] x, int[] h) {
        Coordinate[] result = new Coordinate[x.length * 2];

        int c = 0;
        for(int i = 0; i < x.length; i++ ) {
            House house = new House(h[i]);
            result[c++] = new Coordinate(x[i][0], house, true);
            result[c++] = new Coordinate(x[i][1], house, false);
        }

        Arrays.sort(result);
        return result;
    }

    private static class Coordinate implements Comparable<Coordinate> {
        int x;
        House house;
        boolean opening;

        public Coordinate (int x, House h, boolean o) {
            this.x = x;
            this.house = h;
            this.opening = o;
        }

        public int compareTo(Coordinate other) {
            return Integer.compare(x, other.x);
        }
    }
    private static class House  implements Comparable<House>{
        int height;
        public House(int h) {
            this.height = h;
        }

        public int compareTo(House other) {
            return Integer.compare(other.height, this.height);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            House house = (House) o;

            if (height != house.height) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return height;
        }
    }

    public static void main(String[] args) {
        int[][] x = {{0, 4}, {1, 2}, {1, 5}, {3, 6}, {7, 10}, {7, 8}, {9, 10}};
        int[] h = {3, 2, 5, 4, 5, 7, 6};
        printSkyLine(x, h);
    }
}

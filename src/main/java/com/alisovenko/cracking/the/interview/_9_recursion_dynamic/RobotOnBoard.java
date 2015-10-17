package com.alisovenko.cracking.the.interview._9_recursion_dynamic;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Imagine a robot sitting on the upper left comer of an X by Ygrid. The robot can only move in two directions: right
 * and down. How many possible paths are there for the robot to go from (0,0) to (X, Y)?
 *
 * Изначально считал количество путей, поэтому параллельно возращаю int.
 * Принцип: на каждом шаге обозреваю левый и верхний элемент. Там уже есть пути, как туда добраться. В текущую ячейку
 * копирую эти пути и добавляю к каждому текущий элемент
 *
 * @author alisovenko 12.10.14
 */
public class RobotOnBoard {
    public static int numberOfPaths(int x, int y, int[][] cache, Map<Point, List<List<Point>>> paths) {
        if (x < 0) {
            return 0;
        }
        if (y < 0) {
            return 0;
        }
        if (x == 0 && y == 0) {
            paths.put(new Point(x, y), Collections.singletonList(Lists.newArrayList(new Point(x, y))));
            return 1;
        }

        if (cache[x][y] > 0) {

            return cache[x][y];
        }

        cache[x][y] = numberOfPaths(x - 1, y, cache, paths) + numberOfPaths(x, y - 1, cache, paths);

        cloneLeftPaths(x, y, paths);
        cloneTopPaths(x, y, paths);

        return cache[x][y];

    }

    private static void cloneTopPaths(int x, int y, Map<Point, List<List<Point>>> paths) {
        clonePaths(x, y, paths, false);
    }

    private static void cloneLeftPaths(int x, int y, Map<Point, List<List<Point>>> paths) {
        clonePaths(x, y, paths, true);
    }

    private static void clonePaths(int x, int y, Map<Point, List<List<Point>>> paths, boolean left) {
        List<List<Point>> clonedPaths = new ArrayList<>();
        List<List<Point>> lists = paths.get(new Point(left ? x - 1 : x, left ? y : y - 1));
        if (lists == null) {
            return;
        }
        for (final List<Point> points : lists) {
            List<Point> path = new ArrayList<>(points);
            path.add(new Point(x, y));
            clonedPaths.add(path);
        }
        if (paths.containsKey(new Point(x, y))) {
            paths.get(new Point(x, y)).addAll(clonedPaths);
        } else {
            paths.put(new Point(x, y), clonedPaths);
        }
    }

    public static void main(String[] args) {
        Map<Point, List<List<Point>>> paths = new HashMap<>();
        System.out.println(numberOfPaths(3, 3, init(new int[4][4]), paths));

        for (List<Point> path : paths.get(new Point(3, 3))) {
            path.forEach(p -> System.out.printf("%d,%d -> ", p.x, p.y));
            System.out.println();
        }
//        System.out.println(numberOfPaths(4, 3, init(new int[5][4])));
//        System.out.println(numberOfPaths(4, 5, init(new int[5][6])));
    }

    private static int[][] init(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = -1;
            }
        }
        return ints;
    }

    private static List<Point> newPath(int x, int y) {
        return new ArrayList<>(x * y);
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {

            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return x +
                    "," + y;
        }
    }
}

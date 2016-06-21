package com.alisovenko.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author alisovenko
 *         2/23/16.
 */
public class WallsAndGates {
    private static final int INF = 2147483647;

    public void wallsAndGates(int[][] rooms) {
        Queue<Integer> queue = new LinkedList<>();
        int n = rooms.length;
        int m = rooms[0].length;

        // search for '0' cells and put them to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) queue.offer(i * m + j);
            }
        }
        // Standard BFS
        int level = 0;
        while (!queue.isEmpty()) {
            int k = queue.size();
            while (k > 0) {
                int p = queue.poll();
                int x = p / m;
                int y = p % m;
                if (rooms[x][y] == INF || rooms[x][y] == 0) {
                    rooms[x][y] = level;

                    enqueue(queue, rooms, x - 1, y, n, m);
                    enqueue(queue, rooms, x, y - 1, n, m);
                    enqueue(queue, rooms, x + 1, y, n, m);
                    enqueue(queue, rooms, x, y + 1, n, m);
                }
                k--;
            }
            level++;
        }
    }

    private void enqueue(Queue<Integer> queue, int[][] rooms, int x, int y, int n, int m) {
        if (x < 0 || x >= n || y < 0 || y >= m || rooms[x][y] != INF) return;
        queue.offer(x * m + y);
    }

    public static void main(String[] args) {
        int[][] rooms = {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}};
        new WallsAndGates().wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));
    }
}

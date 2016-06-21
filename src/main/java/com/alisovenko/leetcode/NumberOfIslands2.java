package com.alisovenko.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * @author alisovenko
 *         1/29/16.
 */
public class NumberOfIslands2 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            int h = positions[i][0];
            int w = positions[i][1];

            int coord = h * n + w;
            if (uf.find(coord) == -1) uf.add(coord);
            else throw new IllegalArgumentException("The following coordinates were already added! " + h + " " + w);

            tryEdge(h - 1, w, uf, m, n, coord);
            tryEdge(h, w - 1, uf, m, n, coord);
            tryEdge(h + 1, w, uf, m, n, coord);
            tryEdge(h, w + 1, uf, m, n, coord);

            result.add(uf.setsNumber);
        }
        return result;
    }

    private void tryEdge(int h, int w, UnionFind uf, int m, int n, int coord) {
        if (h < 0 || w < 0 || h >= m || w >= n || uf.find(h * n + w) < 0 || uf.find(h * n + w) == uf.find(coord)) return;
        uf.union(h * n + w, coord);
    }

    private static class UnionFind {
        int[] set = new int[65536];
        // the size of the set rooted at i
        int[] size = new int[65536];
        int setsNumber = 0;

        public UnionFind() {
            for (int i = 0; i < set.length; i++) {
                set[i] = -1;
            }
        }
        public void add(int c) {
            if (set[c] >= 0) throw new IllegalStateException();
            set[c] = c;
            size[c] = 1;
            setsNumber++;
        }

        public int find(int c) {
            if (set[c] == -1) return -1;
            if (set[c] == c) return c;
            // Applying archiving
            return (set[c] = find(set[c]));
        }

        public void union(int c, int d) {
            if (c == d) throw new IllegalArgumentException("" + c + " == " + d + "!");
            int root1 = find(c);
            int root2 = find(d);

            if (size[root1] > size[root2]) {
                set[root2] = root1;
                size[root1] += size[root2];
            } else {
                set[root1] = root2;
                size[root2] += size[root1];
            }
            setsNumber--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfIslands2().numIslands2(3, 3, new int[][]{{0, 1}, {1, 2}, {2, 1}, {1, 0},{0,2},{0,0},{1,1}}));
    }
}

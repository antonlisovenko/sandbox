package com.alisovenko.careerup;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.careercup.com/question?id=5739588970610688
 *
 * Union-find does not work!
 *
 * @author alisovenko 14.10.14
 */
public class ClusterizeNumbersUF {
    public static void clusterize(char[][] p) {
        UF uf = new UF();

        for (int i = 0; i < p.length; i++) {
            char x = p[i][0];
            char y = p[i][1];
            uf.makeSet(x);
            uf.makeSet(y);
            uf.union(x, y);
        }

        for (int i = 0; i < p.length; i++) {
            uf.find(p[i][0]);
            uf.find(p[i][1]);
        }
        uf.print();
    }
    private static class UF {
        char[] uf = new char[255];
        int[] s = new int[255];

        public void makeSet(char x) {
            uf[x] = x;
        }

        // union with ranking
        public void union(char x, char y) {
            x = find(x);
            y = find(y);

            if (s[x] < s[y]) {
                uf[x] = y;
                s[x] += s[y];
            }
            else {
                uf[y] = x;
                s[y] += s[x];
            }
        }

        // find with path compression
        private char find(char x) {
            if (uf[x] != x) {
                uf[x] = find(uf[x]);
            }
            return uf[x];
        }

        public void print() {
            for (int i = 0; i < uf.length; i++) {
                if (uf[i] > 0) System.out.println((char) i + " - " + uf[i]);
            }
        }
    }


    public static void main(String[] args) {
        char[][] input = {
                {'a', 'b'},
                {'c', 'd'},
                {'e', 'f'},
                {'g', 'h'},
                {'a', 'd'},
                {'f', 'g'}
        };
        clusterize(input);
    }
}

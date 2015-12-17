package com.alisovenko.hackerrank;

import java.util.LinkedHashSet;

/**
 * @author alisovenko
 *         11/21/15.
 */
class Graph {
    final LinkedHashSet<Integer>[] adjacencyList;

    Graph(int N) {
        // N + 1 as vertices enumerated starting from 1
        this.adjacencyList = new LinkedHashSet[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adjacencyList[i] = new LinkedHashSet<>();
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        adjacencyList[to].add(from);
    }

    public LinkedHashSet<Integer> children(int v) {
        return adjacencyList[v];
    }

    public int N() {
        return adjacencyList.length;
    }
}

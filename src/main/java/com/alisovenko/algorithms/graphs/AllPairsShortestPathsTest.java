package com.alisovenko.algorithms.graphs;

import com.alisovenko.algorithms.graphs.model.Edge;
import com.alisovenko.algorithms.graphs.model.Graph;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA. User: alisovenko Date: 20.01.13 Time: 2:33 To change this template use File | Settings |
 * File Templates.
 */
public class AllPairsShortestPathsTest {
    public static void main(String[] args) throws IOException {
        doGraph("data/allPairs/g1.txt");
        doGraph("data/allPairs/g2.txt");
        doGraph("data/allPairs/g3.txt");

    }

    private static void doGraph(String s) throws IOException {
        // 1. Read graph
        Graph graph = Utils.readGraph(s);

        // 2. Check for negative cycles
        long g1 = buildFloydWarshall(graph);

        if (g1 == 0) {
            System.out.println("Negative cycle!");
        } else {
            System.out.println("Minimum path: " + g1);
        }
    }

    private static long buildFloydWarshall(Graph graph) {
        int size = graph.size() + 1;
        long[][] m = new long[size][size];

        // Initialize all paths to infinity
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }

        // For all self-paths the weight is 0
        for (int i = 0; i < size; i++) {
            m[i][i] = 0;
        }
        Iterator<Edge> edgeIterator = graph.edgesIterator();

        // Setting all weights that are set
        // Note, that values of vertexes (ids) are the indexes for the array. This can be applied ONLY for dense graphs!
        // (those where vertexes are close to each other)
        while (edgeIterator.hasNext()) {
            Edge next = edgeIterator.next();
            m[next.from.id][next.to.id] = next.weight;
        }

        // Main cycle
        long minLength = Long.MAX_VALUE;
        for (int k = 1; k < size; k++) {
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    if (m[i][j] > m[i][k] + m[k][j]) {
                        m[i][j] = m[i][k] + m[k][j];
                    }
                    if (m[i][j] < minLength) {
                        minLength = m[i][j];
                    }
                }
            }
        }

        // Checking for negative length cycles
        for (int i = 1; i < size; i++) {
            if (m[i][i] < 0) {
                System.out.println("Negative! " + m[i][i]);
                return 0;
            }
        }

        return minLength;
    }

}

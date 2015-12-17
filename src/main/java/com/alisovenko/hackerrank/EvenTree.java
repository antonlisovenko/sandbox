package com.alisovenko.hackerrank;

import java.util.*;

/**
 * @author alisovenko
 *         11/21/15.
 */
public class EvenTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int N = in.nextInt();
        int M = in.nextInt();

        Graph g = new Graph(N);

        for (int i = 0; i < M; i++) {
            g.addEdge(in.nextInt(), in.nextInt());
        }

        int breaks = 0;

        Integer v;
        while ((v = g.vertexWithTwoEdges()) != null) {
            int other = findOutVertexForCut(g, v);
        }
    }

    private static int findOutVertexForCut(Graph g, int v) {
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        HashSet<Integer> children = g.children(v);
        assert children.size() == 2;

        queue.addFirst(v);

        int counter = 0;
        while (!queue.isEmpty()) {
            int t = queue.removeLast();

            for (Integer ch : g.children(t)) {
                if (!visited.contains(ch)) {
                    counter++;
                    queue.addFirst(ch);
                    visited.add(ch);
                }
            }
        }

        return 0;
    }

    static class Graph {
        final HashSet<Integer>[] adjacencyList;
        final Set<Integer> verticesWithTwoEdges = new HashSet<>();

        Graph(int N) {
            // N + 1 as vertices enumerated starting from 1
            this.adjacencyList = new HashSet[N + 1];
            for (int i = 1; i < N + 1; i++) {
                adjacencyList[i] = new HashSet<>();
            }
        }

        public void addEdge(int from, int to) {
            adjacencyList[from].add(to);
            adjacencyList[to].add(from);
            checkEdges(from);
            checkEdges(to);
        }

        public void removeEdge(int from, int to) {
            adjacencyList[from].remove(from);
            adjacencyList[to].remove(to);
            checkEdges(from);
            checkEdges(to);
        }

        private void checkEdges(int v) {
            if (children(v).size() == 2) {
                verticesWithTwoEdges.add(v);
            } else {
                verticesWithTwoEdges.remove(v);
            }
        }

        public Integer vertexWithTwoEdges() {
            return verticesWithTwoEdges.isEmpty() ? null : verticesWithTwoEdges.iterator().next();
        }

        public HashSet<Integer> children(int v) {
            return adjacencyList[v];
        }

        public int N() {
            return adjacencyList.length;
        }
    }
}

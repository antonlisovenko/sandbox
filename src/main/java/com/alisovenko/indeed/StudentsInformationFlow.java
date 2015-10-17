package com.alisovenko.indeed;

import java.util.*;

/**
 * @author alisovenko 10.01.15
 */
public class StudentsInformationFlow {

    private static class Graph {
        int [][] graph;

        private Graph(int n) {
            graph = new int[n][n];
        }

        public void addEdge(int x, int y) {
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        public int[] adjacents(int x) {
            return graph[x];
        }

        public int N() {
            return graph.length;
        }
    }
    private static class Paths {
        List<Integer> pathLengths = new ArrayList<>();

        public Paths(Graph graph) {
            search(graph, 0, new LinkedHashSet<Integer>());
        }

        private void search(Graph graph, int v, HashSet<Integer> path) {
            path.add(v);
            int[] adjacents = graph.adjacents(v);

            boolean recursed = false;
            for (int i = 0; i < adjacents.length; i++) {
                if (adjacents[i] > 0  && !path.contains(i)) {
                    recursed = true;
                    search(graph, i, path);
                }
            }

            if (!recursed) {
                // we found the complete path
                pathLengths.add(path.size());
            }
            // let's remove current element
            path.remove(v);
        }

        public double avgPathLength() {
            double cnt = 0;
            for (final int size : pathLengths) {
                cnt += size;
            }
            return cnt/ pathLengths.size();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();

        Graph graph = new Graph(N);

        for (int i = 0; i < N; i++) {
            populateGraph(i, scanner.nextLine(), graph);
        }

        Paths paths = new Paths(graph);

        System.out.println(paths.avgPathLength());
    }

    private static void populateGraph(int p, String s, Graph graph) {
        char[] chars = s.trim().toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'Y') {
                graph.addEdge(p, i);
            }
        }
    }
}

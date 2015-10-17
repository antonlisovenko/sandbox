package com.alisovenko.indeed;

import java.util.*;

/**
 * @author alisovenko 10.01.15
 */
public class CheckingCable {
    private static class Graph {
        Map<Integer, List<Edge>> map = new HashMap<>();

        public void addEdge(int from, int to, int weight) {
            if (map.containsKey(from)) {
                map.get(from).add(new Edge(weight, from, to));
            } else {
                List<Edge> edges = new ArrayList<>();
                edges.add(new Edge(weight, from, to));
                map.put(from, edges);
            }
            if (map.containsKey(to)) {
                map.get(to).add(new Edge(weight, to, from));
            } else {
                List<Edge> edges = new ArrayList<>();
                edges.add(new Edge(weight, to, from));
                map.put(to, edges);
            }
        }

        public boolean traverse(int from, int to, int w, Set<Integer> visited) {
            List<Edge> edges = map.get(from);
            visited.add(from);

            if (edges != null) {
                for (final Edge edge : edges) {
                    if (visited.contains(edge.y)) {
                        // to avoid loops
                        continue;
                    }
                    if (edge.y == to && edge.weight >= w) {
                        return true;
                    }

                    if (edge.weight >= w) {
                        if (traverse(edge.y, to, w, visited)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }

    }

    private static class Edge {
        final int weight;
        final int x;
        final int y;

        private Edge(int weight, int x, int y) {
            this.weight = weight;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static Graph graph = new Graph();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfComputers = scanner.nextInt();
        int numberOfCommands = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfCommands; i++) {
            performCommand(numberOfComputers, scanner.nextLine());
        }
    }

    private static void performCommand(int numberOfComputers, String s) {
        String[] tokens = s.split(" ");
        if (tokens[0].equals("make")) {
            doMake(numberOfComputers, tokens);
        } else {
            doCheck(numberOfComputers, tokens);
        }
    }

    private static void doCheck(int numberOfComputers, String[] tokens) {
        try {
            Integer from = Integer.valueOf(tokens[1]);
            Integer to = Integer.valueOf(tokens[2]);
            Integer w = Integer.valueOf(tokens[3]);

            if (from > numberOfComputers || to > numberOfComputers) {
                // throw new IllegalArgumentException
                System.out.println("NO");
                return;
            }
            if (graph.traverse(from, to, w, new HashSet<Integer>())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (NumberFormatException e) {
            System.out.println("NO");
        }
    }

    private static void doMake(int numberOfComputers, String[] tokens) {
        try {
            Integer from = Integer.valueOf(tokens[1]);
            Integer to = Integer.valueOf(tokens[2]);
            Integer weight = Integer.valueOf(tokens[3]);

            if (from > numberOfComputers || to > numberOfComputers) {
                // throw new IllegalArgumentException
                return;
            }
            graph.addEdge(from, to, weight);
        } catch (NumberFormatException e) {
            return;
        }
    }
}

package com.alisovenko.hackerrank;

import java.util.*;

/**
 * @author alisovenko
 *         11/21/15.
 */
public class BreadthFirstSearch {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("[\t\n ]+");
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            Graph g = new Graph(in.nextInt());
            int edgesNumber = in.nextInt();

            for (int j = 0; j < edgesNumber; j++) {
                g.addEdge(in.nextInt(), in.nextInt());
            }

            bfs(g, in.nextInt());
        }
    }

    private static void bfs(Graph g, int startVertex) {
        LinkedList<Integer> vQueue = new LinkedList<>();
        LinkedList<Integer> dQueue = new LinkedList<>();

        vQueue.addFirst(startVertex);
        dQueue.addFirst(0);

        int[] dist = new int[g.N()];
        dist = Arrays.stream(dist).map(c -> c - 1).toArray(); // initialize all members to -1

        while (!vQueue.isEmpty()) {
            int v = vQueue.removeLast();
            int d = dQueue.removeLast();

            for (Integer i : g.children(v)) {
                if (dist[i] < 0 && i != startVertex) {
                    dist[i] = d + 6;
                    vQueue.addFirst(i);
                    dQueue.addFirst(d + 6);
                }
            }
        }

        for (int i = 1; i < g.N(); i++) {
            if (i != startVertex) {
                System.out.print("" + dist[i] + ' ');
            }
        }
        System.out.println();
    }
}

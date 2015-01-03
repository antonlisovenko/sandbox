package com.alisovenko.careerup;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.careercup.com/question?id=5739588970610688
 *
 * @author alisovenko 14.10.14
 */
public class ClusterizeNumbers {
    public static void clusterize(char[][] pairs) {
        // build graph
        List<Character>[] graph = buildGraph(pairs);

        char[] marked = new char[255];

        for (int i = 0; i < graph.length; i++) {
            if (marked[i] == 0 && graph[i] != null) {
                // we have not come to this letter yet
                dfsAndPrint(i, graph, marked);
                System.out.println();
            }
        }
    }

    private static void dfsAndPrint(int i, List<Character>[] graph, char[] marked) {
        if (marked[i] > 0) {
            return;
        }
        System.out.printf("%s, ", (char)i);
        marked[i] = 1;

        if (graph[i] != null) {
            for (Character c : graph[i]) {
                dfsAndPrint(c, graph, marked);
            }
        }
    }

    private static List<Character>[] buildGraph(char[][] pairs) {
        List<Character>[] graph = new ArrayList[255];

        for (final char[] pair : pairs) {
            addToGraph(graph, pair[0], pair[1]);
            addToGraph(graph, pair[1], pair[0]);
        }
        return graph;
    }

    public static void addToGraph(List<Character>[] graph, char v1, char v2) {
        List<Character> characters = graph[v1];
        if (characters == null) {
            characters = new ArrayList<>();
            graph[v1] = characters;
        }
        characters.add(v2);
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

package com.alisovenko.penguin.google.onsite;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Был словарик со словами, упорядоченными по алфавиту. Например, 
 cat, cats, dog, dogs, the, there.

 Эти слова зашифровали заменой букв, но сохранили их порядок. Получилось, например,

 rth, rthf, uik, uikf, hpo, hpoqo.

 Нам дан только последний список. Необходимо по нему определить частичный порядок замен с возможными неопределённостями. Короче говоря, для данного примера это будет "ruh", что означает, что расшифровка буквы r по алфавиту идёт до расшифровки u, а расшифровка той по алфавиту идёт до расшифровки h. Но вообще возможны варианты типа "t[kx]bc", когда относительный порядок k и x неизвестен, но точно известно, что они оба после t и перед b и c.
 В некоторых экземплярах задачи возможны ответы типа "abc[def[ghi]]jkl", но от такой вложенности решили отказаться, так как это слишком сложно становится. В данном случае мы будем счастливы ответу "abc[def][ghi]jkl".

 * @author alisovenko 05.01.15
 */
public class RelativeOrdering {
    public static String findRelativeOrder(List<String> strings) {
        DiGraph graph = new DiGraph();

        for (int i = 0; i < strings.size() - 1; i++) {
            // compare each string with next one per character
            char[] first = strings.get(i).toCharArray();
            char[] second = strings.get(i + 1).toCharArray();

            for (int p = 0; p < Math.min(first.length, second.length); p++) {
                if (first[p] != second[p]) {
                    graph.addEdge(first[p], second[p]);
                    break;
                }
            }
        }

        // got graph, now let's do DFS and topological sort print all characters
        return new Dfs(graph).topologicalOrder().toString();
    }

    public static final Set<Character> MOCK = new HashSet<>();
    private static class DiGraph {
        Set[] nodes = new HashSet[256];

        public DiGraph() {
            for (int i = 0; i < 255; i++) {
                nodes[i] = MOCK;
            }
        }

        public void addEdge(char from, char to) {
            System.out.printf("Adding edge %s -> %s\n", from , to);
            if (nodes[from] == MOCK) {
                nodes[from] = new HashSet<>();
            }
            if (nodes[to] == MOCK) {
                nodes[to] = new HashSet<>();
            }
            nodes[from].add(to);
        }

        public Set<Character> allAdjacents(char v) {
            return nodes[v];
        }

    }

    private static class Dfs {
        boolean[] marked = new boolean[256];
        char[] pathTo = new char[256];
        LinkedList<Character> topological = new LinkedList<>();

        public Dfs(DiGraph graph) {
            for (int i = 0; i < 255; i++) {
                if (!marked[i]) {
                    doDfs(graph, (char) i);
                }
            }
        }

        private void doDfs(DiGraph graph, char vertex) {
            Set<Character> characters = graph.allAdjacents(vertex);
            for (char next : characters) {
                if (!marked[next]) {
                    pathTo[next] = vertex;
                    marked[next] = true;

                    doDfs(graph, next);
                }
            }
            marked[vertex] = true;

            if (characters != MOCK) {
                topological.addFirst(vertex);
            }
        }

        public List<Character> topologicalOrder() {
            return topological;
        }

    }

    public static void main(String[] args) {
        System.out.println(findRelativeOrder(Lists.newArrayList("rth", "rthf", "rtp", "uik", "uikf", "hpo", "hpoqo", "s")));
    }
}

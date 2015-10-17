package com.alisovenko.leetcode;

import com.google.common.collect.Sets;

import java.util.*;
public class WordLadder2 {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // length of each word
        int M = dict.iterator().next().length();

        Graph graph = new Graph();
        for (int i = 0; i < M; i++) {
            Map<String, Set<String>> map = new HashMap<>();

            // populate the map with similar words
            for (String s : dict) {
                String sample = i == 0 ? s.substring(i + 1) : s.substring(0, i) + s.substring(i + 1);
                Set<String> set = map.get(sample);
                if (set == null) {
                    set = new HashSet<>();
                    map.put(sample, set);
                }
                set.add(s);
            }
            // move just found cluster to graph
            for (Set<String> c: map.values()) {
                for (String s: c) {
                    for (String s2: c) {
                        if (!s.equals(s2)) {
                            graph.add(s, s2);
                        }
                    }
                }
            }
        }

        // Now find all shortest paths
        return findAllShortestPaths(graph, start, end);
    }


    private static Object mock = new Object();
    public static List<List<String>> findAllShortestPaths(Graph graph, String from, String to) {
        LinkedHashMap<Node, Object> queue = new LinkedHashMap<>();
        Set<Node> visited = new HashSet<>();
        queue.put(new Node(from, 0), mock);

        Node nodeTo = null;
        while (queue.keySet().size() > 0) {
            Node next = queue.keySet().iterator().next();

            if (next.key.equals(to)) {
                // base case: we found the end node and processed all edges to it -> we are done
                nodeTo = next;
                break;
            }

            for (Node n : graph.adjacents(next.key)) {
                if (!visited.contains(n)) {
                    if (queue.get(n) == null) {
                        queue.put(n, mock);
                    }
                    n.addParent(next);
                }
            }

            // removing the node from queue
            queue.remove(next);
            visited.add(next);
        }
        if (nodeTo == null) {
            return Collections.emptyList();
        }

        // Now performing the dfs from target node to gather all paths
        List<List<String>> result = new ArrayList<>();
        dfs(nodeTo, result, new LinkedList<>());

        return result;
    }

    private static void dfs(Node n, List<List<String>> result, LinkedList<String> path) {
        path.addFirst(n.key);
        if (n.getParents().size() == 0) {
            // base case: we came to target vertex
            result.add(new ArrayList<>(path));
        }
        for (Node p : n.getParents()) {
            dfs(p, result, path);
        }
        // do not forget to remove the processed element from path
        path.removeFirst();
    }

    private static class Graph {
        Map<String, Set<Node>> adjacencyList = new HashMap<>();

        public void add(String from, String to) {
            addEdge(to, from);
            addEdge(from, to);
        }

        private void addEdge(String from, String to) {
            Set<Node> list = adjacencyList.get(from);

            if (list == null) {
                list = new LinkedHashSet<>();
                adjacencyList.put(from, list);
            }
            list.add(Node.get(to));
        }

        public Set<Node> adjacents(String v) {
            Set<Node> nodes = adjacencyList.get(v);
            return nodes == null ? Collections.emptySet() : nodes;
        }
    }

    private static class Node {
        List<Node> parents = new ArrayList<>();
        private static Map<String, Node> map = new HashMap<>();
        private final String key;
        int level = -1;

        public static Node get(String str) {
            // inner interning of nodes
            Node res = map.get(str);
            if (res == null) {
                res = new Node(str, -1);
                map.put(str, res);
            }
            return res;
        }

        private Node(String str, int level) {
            key = str;
            this.level = level;
        }

        public void addParent(Node n) {
            // forbidding the parent it its level is equal to ours
            if (n.level == level) {
                return;
            }
            parents.add(n);

            level = n.level + 1;
        }

        public List<Node> getParents() {
            return parents;
        }

        public boolean equals(Object n) {
            return key.equals(((Node) n).key);
        }

        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return key;
        }
    }

    public static void main(String[] args) {
        System.out.println(new WordLadder2().findLadders("hot", "dog", Sets.newHashSet("hot", "dog")));
        System.out.println(new WordLadder2().findLadders("hot", "dog", Sets.newHashSet("hot", "cog", "dot", "dog", "hit", "lot", "log")));
        System.out.println(new WordLadder2().findLadders("cat", "fin", Sets.newHashSet("ion","rev","che","ind","lie","wis","oct","ham","jag","ray","nun","ref","wig","jul","ken","mit","eel","paw","per","ola","pat","old","maj","ell","irk","ivy","beg","fan","rap","sun","yak","sat","fit","tom","fin","bug","can","hes","col","pep","tug","ump","arc","fee","lee","ohs","eli","nay","raw","lot","mat","egg","cat","pol","fat","joe","pis","dot","jaw","hat","roe","ada","mac")));
    }
}
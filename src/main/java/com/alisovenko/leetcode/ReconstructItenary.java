package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/22/16.
 */
public class ReconstructItenary {
    private int c = 0;
    public List<String> findItinerary(String[][] tickets) {
        Map<String, Set<String>> graph = createGraph(tickets);
//        String end = graph.entrySet().stream().filter(e -> e.getValue().isEmpty()).findAny().get().getKey();

        return findRecursively(graph, tickets.length, new HashSet<>(), "JFK", new ArrayList<>());
    }

    List<String> findRecursively(Map<String, Set<String>> graph, int edgesSize, Set<String> visitedEdges, String city, List<String> result) {
        result.add(city);
        // Base case - we-ve built the route
        if (edgesSize == visitedEdges.size()) return result;

        for (String adjacent: graph.get(city)) {
            if (!visitedEdges.contains(city + adjacent)) {
                visitedEdges.add(city + adjacent);

                System.out.printf("(%d) Trying [%s -> %s], path:%s\n", c++, city, adjacent, result);

                List<String> res = findRecursively(graph, edgesSize, visitedEdges, adjacent, result);
                if (res != null) return res;

                visitedEdges.remove(city + adjacent);
            }
        }
        result.remove(result.size() - 1);
        return null;
    }

    Map<String, Set<String>> createGraph(String[][] tickets) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String[] edge: tickets) {
            graph.putIfAbsent(edge[0], new TreeSet<>());
            graph.putIfAbsent(edge[1], new TreeSet<>());
            graph.get(edge[0]).add(edge[1]);
        }
        System.out.printf("Graph: size %d, %s\n", graph.size(), graph);
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(new ReconstructItenary().findItinerary(new String[][]{new String[]{"AXA", "EZE"}, new String[]{"EZE", "AUA"}, new String[]{"ADL", "JFK"}, new String[]{"ADL", "TIA"}, new String[]{"AUA", "AXA"}, new String[]{"EZE", "TIA"}, new String[]{"EZE", "TIA"}, new String[]{"AXA", "EZE"}, new String[]{"EZE", "ADL"}, new String[]{"ANU", "EZE"}, new String[]{"TIA", "EZE"}, new String[]{"JFK", "ADL"}, new String[]{"AUA", "JFK"}, new String[]{"JFK", "EZE"}, new String[]{"EZE", "ANU"}, new String[]{"ADL", "AUA"}, new String[]{"ANU", "AXA"}, new String[]{"AXA", "ADL"}, new String[]{"AUA", "JFK"}, new String[]{"EZE", "ADL"}, new String[]{"ANU", "TIA"}, new String[]{"AUA", "JFK"}, new String[]{"TIA", "JFK"}, new String[]{"EZE", "AUA"}, new String[]{"AXA", "EZE"}, new String[]{"AUA", "ANU"}, new String[]{"ADL", "AXA"}, new String[]{"EZE", "ADL"}, new String[]{"AUA", "ANU"}, new String[]{"AXA", "EZE"}, new String[]{"TIA", "AUA"}, new String[]{"AXA", "EZE"}, new String[]{"AUA", "SYD"}, new String[]{"ADL", "JFK"}, new String[]{"EZE", "AUA"}, new String[]{"ADL", "ANU"}, new String[]{"AUA", "TIA"}, new String[]{"ADL", "EZE"}, new String[]{"TIA", "JFK"}, new String[]{"AXA", "ANU"}, new String[]{"JFK", "AXA"}, new String[]{"JFK", "ADL"}, new String[]{"ADL", "EZE"}, new String[]{"AXA", "TIA"}, new String[]{"JFK", "AUA"}, new String[]{"ADL", "EZE"}, new String[]{"JFK", "ADL"}, new String[]{"ADL", "AXA"}, new String[]{"TIA", "AUA"}, new String[]{"AXA", "JFK"}, new String[]{"ADL", "AUA"}, new String[]{"TIA", "JFK"}, new String[]{"JFK", "ADL"}, new String[]{"JFK", "ADL"}, new String[]{"ANU", "AXA"}, new String[]{"TIA", "AXA"}, new String[]{"EZE", "JFK"}, new String[]{"EZE", "AXA"}, new String[]{"ADL", "TIA"}, new String[]{"JFK", "AUA"}, new String[]{"TIA", "EZE"}, new String[]{"EZE", "ADL"}, new String[]{"JFK", "ANU"}, new String[]{"TIA", "AUA"}, new String[]{"EZE", "ADL"}, new String[]{"ADL", "JFK"}, new String[]{"ANU", "AXA"}, new String[]{"AUA", "AXA"}, new String[]{"ANU", "EZE"}, new String[]{"ADL", "AXA"}, new String[]{"ANU", "AXA"}, new String[]{"TIA", "ADL"}, new String[]{"JFK", "ADL"}, new String[]{"JFK", "TIA"}, new String[]{"AUA", "ADL"}, new String[]{"AUA", "TIA"}, new String[]{"TIA", "JFK"}, new String[]{"EZE", "JFK"}, new String[]{"AUA", "ADL"}, new String[]{"ADL", "AUA"}, new String[]{"EZE", "ANU"}, new String[]{"ADL", "ANU"}, new String[]{"AUA", "AXA"}, new String[]{"AXA", "TIA"}, new String[]{"AXA", "TIA"}, new String[]{"ADL", "AXA"}, new String[]{"EZE", "AXA"}, new String[]{"AXA", "JFK"}, new String[]{"JFK", "AUA"}, new String[]{"ANU", "ADL"}, new String[]{"AXA", "TIA"}, new String[]{"ANU", "AUA"}, new String[]{"JFK", "EZE"}, new String[]{"AXA", "ADL"}, new String[]{"TIA", "EZE"}, new String[]{"JFK", "AXA"}, new String[]{"AXA", "ADL"}, new String[]{"EZE", "AUA"}, new String[]{"AXA", "ANU"}, new String[]{"ADL", "EZE"}, new String[]{"AUA", "EZE"}}));
    }
}

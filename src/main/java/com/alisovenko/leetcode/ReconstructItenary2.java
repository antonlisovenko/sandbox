package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/22/16.
 */
public class ReconstructItenary2 {
    Map<String, PriorityQueue<String>> targets = new HashMap<>();
    List<String> route = new LinkedList();

    public List<String> findItinerary(String[][] tickets) {
        for (String[] ticket : tickets)
            targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
        visit("JFK");
        return route;
    }

    void visit(String airport) {
        while (targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }

    public static void main(String[] args) {
        System.out.println(new ReconstructItenary2().findItinerary(new String[][]{new String[]{"AXA", "EZE"}, new String[]{"EZE", "AUA"}, new String[]{"ADL", "JFK"}, new String[]{"ADL", "TIA"}, new String[]{"AUA", "AXA"}, new String[]{"EZE", "TIA"}, new String[]{"EZE", "TIA"}, new String[]{"AXA", "EZE"}, new String[]{"EZE", "ADL"}, new String[]{"ANU", "EZE"}, new String[]{"TIA", "EZE"}, new String[]{"JFK", "ADL"}, new String[]{"AUA", "JFK"}, new String[]{"JFK", "EZE"}, new String[]{"EZE", "ANU"}, new String[]{"ADL", "AUA"}, new String[]{"ANU", "AXA"}, new String[]{"AXA", "ADL"}, new String[]{"AUA", "JFK"}, new String[]{"EZE", "ADL"}, new String[]{"ANU", "TIA"}, new String[]{"AUA", "JFK"}, new String[]{"TIA", "JFK"}, new String[]{"EZE", "AUA"}, new String[]{"AXA", "EZE"}, new String[]{"AUA", "ANU"}, new String[]{"ADL", "AXA"}, new String[]{"EZE", "ADL"}, new String[]{"AUA", "ANU"}, new String[]{"AXA", "EZE"}, new String[]{"TIA", "AUA"}, new String[]{"AXA", "EZE"}, new String[]{"AUA", "SYD"}, new String[]{"ADL", "JFK"}, new String[]{"EZE", "AUA"}, new String[]{"ADL", "ANU"}, new String[]{"AUA", "TIA"}, new String[]{"ADL", "EZE"}, new String[]{"TIA", "JFK"}, new String[]{"AXA", "ANU"}, new String[]{"JFK", "AXA"}, new String[]{"JFK", "ADL"}, new String[]{"ADL", "EZE"}, new String[]{"AXA", "TIA"}, new String[]{"JFK", "AUA"}, new String[]{"ADL", "EZE"}, new String[]{"JFK", "ADL"}, new String[]{"ADL", "AXA"}, new String[]{"TIA", "AUA"}, new String[]{"AXA", "JFK"}, new String[]{"ADL", "AUA"}, new String[]{"TIA", "JFK"}, new String[]{"JFK", "ADL"}, new String[]{"JFK", "ADL"}, new String[]{"ANU", "AXA"}, new String[]{"TIA", "AXA"}, new String[]{"EZE", "JFK"}, new String[]{"EZE", "AXA"}, new String[]{"ADL", "TIA"}, new String[]{"JFK", "AUA"}, new String[]{"TIA", "EZE"}, new String[]{"EZE", "ADL"}, new String[]{"JFK", "ANU"}, new String[]{"TIA", "AUA"}, new String[]{"EZE", "ADL"}, new String[]{"ADL", "JFK"}, new String[]{"ANU", "AXA"}, new String[]{"AUA", "AXA"}, new String[]{"ANU", "EZE"}, new String[]{"ADL", "AXA"}, new String[]{"ANU", "AXA"}, new String[]{"TIA", "ADL"}, new String[]{"JFK", "ADL"}, new String[]{"JFK", "TIA"}, new String[]{"AUA", "ADL"}, new String[]{"AUA", "TIA"}, new String[]{"TIA", "JFK"}, new String[]{"EZE", "JFK"}, new String[]{"AUA", "ADL"}, new String[]{"ADL", "AUA"}, new String[]{"EZE", "ANU"}, new String[]{"ADL", "ANU"}, new String[]{"AUA", "AXA"}, new String[]{"AXA", "TIA"}, new String[]{"AXA", "TIA"}, new String[]{"ADL", "AXA"}, new String[]{"EZE", "AXA"}, new String[]{"AXA", "JFK"}, new String[]{"JFK", "AUA"}, new String[]{"ANU", "ADL"}, new String[]{"AXA", "TIA"}, new String[]{"ANU", "AUA"}, new String[]{"JFK", "EZE"}, new String[]{"AXA", "ADL"}, new String[]{"TIA", "EZE"}, new String[]{"JFK", "AXA"}, new String[]{"AXA", "ADL"}, new String[]{"EZE", "AUA"}, new String[]{"AXA", "ANU"}, new String[]{"ADL", "EZE"}, new String[]{"AUA", "EZE"}}));
    }
}

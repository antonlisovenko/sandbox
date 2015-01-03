package com.alisovenko.cracking.the.interview._4_trees_graphs;

import java.util.Set;

/**
 * @author alisovenko 24.08.14
 */
public class Graph {
    Set<Integer>[] inner;

    public void add(int parent, int element) {
        inner[parent].add(element);
    }
}

package com.alisovenko.algorithms.graphs;

import com.alisovenko.algorithms.graphs.model.Edge;
import com.alisovenko.algorithms.graphs.model.Graph;
import com.alisovenko.algorithms.graphs.model.Vertex;

import java.util.HashSet;

import java.util.Set;

import java.util.LinkedList;

import java.util.Queue;

import java.io.IOException;

/**
 * TBD: add comments for BreadthFirstSearchTree.java.
 *
 * <p>Created: 25.12.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BreadthFirstSearchTree {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 1. Read graph
        Graph graph = Utils.readGraph("data/prim.txt");
        
        Graph bft = new Graph();
        
        Queue<Vertex> queue = new LinkedList<Vertex>();
        Set<Vertex> visited = new HashSet<Vertex>();
        
        queue.add(graph.vertexesIterator().next());
        
        
        Vertex next;
        
        int i = 0;
        while ((next  = queue.poll()) != null) {
            if (next.treeEdge != null) {
                bft.add(next.treeEdge);
                i++;
            }
            else {
                System.out.println("No edge!");
            }
            for (Edge edge : graph.getAdjacents(next)) {
                Vertex to = edge.to;
                if (!visited.contains(to)) {
                    to.treeEdge = edge;
                    queue.add(to);
                    visited.add(to);
                    System.out.println("Current : " + next.id + ", adding adjacent: " + to);
                }
            }
        }
        
        System.out.println("Cnt: " + i);
        System.out.println("Size: " + bft.size());
        System.out.println("Number of edges: " + bft.edgesSize());
    }
}

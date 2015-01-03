package com.alisovenko.algorithms.graphs;

import com.alisovenko.algorithms.graphs.model.Edge;
import com.alisovenko.algorithms.graphs.model.Graph;
import com.alisovenko.algorithms.graphs.model.Vertex;
import com.alisovenko.algorithms.structure.Heap;

import java.io.IOException;
import java.util.Iterator;

/**
 * TBD: add comments for PrimMSTTest.java. <p/> <p>Created: 23.12.2012</p>
 *
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class PrimMSTTest {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 1. Read graph
        Graph graph = Utils.readGraph("data/prim.txt");

        // 2. Compute MST
        Graph mst = new Graph();

        Heap<Vertex> vertexHeap = new Heap<Vertex>();

        // Let's go!

        // Add all vertices to heap
        Iterator<Vertex> iterator = graph.vertexesIterator();
        while (iterator.hasNext()) {
            vertexHeap.add(iterator.next());
        }

        // Starting from arbitrary vertex
        graph.vertexesIterator().next();
        graph.vertexesIterator().next();
        graph.vertexesIterator().next();
        graph.vertexesIterator().next();

        Vertex found = graph.vertexesIterator().next();
        found.updateKey(0);
        // Updating the heap
        vertexHeap.increaseKey(found);

        Vertex next;
        int i = 0;
        while((next = vertexHeap.extractMaxMin()) != null) {
            System.out.println("Extracted vertex " + next);
            if (next.getTreeEdge() == null) {
                System.out.println("Why is null?? " + (i++) + " " + next);
            }
            if (next.getTreeEdge() != null) {
                mst.add(next.getTreeEdge());
            }
            // Decreasing keys for all adjacent vertexes
            for (Edge edge : graph.getAdjacents(next)) {
                Vertex to = edge.to;
                if (!mst.contains(to) && (to.getKey() > (next.getKey() + edge.weight))) {
                    to.updateKey(next.getKey() + edge.weight);
                    to.setTreeEdge(edge);
                    // Updating the heap
                    vertexHeap.increaseKey(to);
                    System.out.println("Chosen adjacent for " + next.id + " is " + to);
                    printHeap(vertexHeap);
                }
            }

            // Ok, we can take the vertex with minimal key and take the edge to parent from it on next iteration
        }
        // 69119377652 67311454237
        System.out.println(mst.length());
        System.out.println(mst.size());
        System.out.println(mst.edgesSize());
    }

    private static void printHeap(Heap<Vertex> heap) {
        StringBuilder b = new StringBuilder();
        int cnt = 0;
        for (Object o : heap.toArray()) {
            Vertex v = (Vertex) o;
            if (v.getKey() == Integer.MAX_VALUE && cnt++ == 5) {
                break;
            }
            b.append( v.getKey() + "(" + v.id + ")").append(", ");

        }
        System.out.println("Heap: " + b.toString());
    }



}

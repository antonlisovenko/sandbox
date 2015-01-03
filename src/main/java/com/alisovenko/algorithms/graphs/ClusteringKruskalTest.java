package com.alisovenko.algorithms.graphs;

import com.alisovenko.algorithms.graphs.model.Edge;
import com.alisovenko.algorithms.graphs.model.Graph;
import com.alisovenko.algorithms.graphs.model.Vertex;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Solution to the problem 1 from https://class.coursera.org/algo2-2012-001/quiz/attempt?quiz_id=79
 * User: alisovenko
 * Date: 03.01.13
 * Time: 3:05
 * To change this template use File | Settings | File Templates.
 */
public class ClusteringKruskalTest {

    public static void main(String[] args) throws IOException {
        // 1. Read graph
        Graph graph = Utils.readGraph("data/clustering1.txt");
        // 2. Sort edges
        TreeSet<Edge> edges = new TreeSet<Edge>(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge, Edge edge1) {
                return edge.weight < edge1.weight ? -1 : 1;
            }
        });

        Iterator<Edge> iterator = graph.edgesIterator();
        while (iterator.hasNext()) {
            Edge next = iterator.next();
            edges.add(next);
        }

        // 3. Create initial clusters (sets)
        Iterator<Vertex> vertexIterator = graph.vertexesIterator();
        while (vertexIterator.hasNext()) {
            Vertex next = vertexIterator.next();
            createSet(next);
        }
        Graph msTree = new Graph();

        int clustersNumber = graph.size();
        Iterator<Edge> edgeIterator = edges.iterator();
        while (clustersNumber > 4) {
            Edge edge = edgeIterator.next();
            Vertex first = edge.from;
            Vertex second = edge.to;
            if (!(findSet(first) == findSet(second))) {
                if (first.id == 92 || second.id == 92) {
                    System.out.println("here");
                }
                System.out.println("Adding edge to kruskal mst: " + edge);
                unionSets(first, second);
                msTree.add(edge);
                clustersNumber--;
            }
            else {
                System.out.println("Skipping edge " + edge);
            }
        }

        // Finally we take the next edge between clusters and take its length as the spacing of the cluster
        int i = 0, maxspacing = 0;
        while (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            Vertex first = edge.from;
            Vertex second = edge.to;
            if (!(findSet(first) == findSet(second))) {
                if (i++ == 0){
                System.out.println("Spacing of the cluster: " + edge.weight);
                }
            maxspacing = edge.weight;
            }
        }
        System.out.println("Maximum spacing of the cluster: " + maxspacing);


    }

    private static final HashMap<Vertex, VertexWrapper> cache = new HashMap<Vertex, VertexWrapper>();


    private static void createSet(Vertex v) {
        VertexWrapper wrapper = new VertexWrapper(v);
        wrapper.parent = wrapper;
        wrapper.rank = 0;
        cache.put(v, wrapper);
    }

    private static VertexWrapper findSet(Vertex v) {
        VertexWrapper current = cache.get(v);
        if (current != current.parent) {
            current.parent = findSet(current.parent.v);
        }
        return current.parent;
    }

        private static void unionSets(Vertex v1, Vertex v2) {
            VertexWrapper wrapper1 = cache.get(v1);
            VertexWrapper wrapper2 = cache.get(v2);

            VertexWrapper root1 = wrapper1.parent;
            VertexWrapper root2 = wrapper2.parent;

            if (root1.rank == root2.rank) {
                root1.parent = root2;
                root2.rank++;
            }
            else if (root1.rank > root2.rank){
                root2.parent = root1;
            }
            else if (root1.rank < root2.rank){
                root1.parent = root2;
        }
    }

    private static class VertexWrapper {
        Vertex v;

        VertexWrapper parent;

        @Override
        public String toString() {
            return "VertexWrapper{" +
                    "v=" + v.id +
                    ", parent=" + parent.v.id +
                    ", rank=" + rank +
                    '}';
        }

        int rank;

        private VertexWrapper(Vertex v) {
            this.v = v;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public void setParent(VertexWrapper parent) {
            this.parent = parent;
        }


    }
}

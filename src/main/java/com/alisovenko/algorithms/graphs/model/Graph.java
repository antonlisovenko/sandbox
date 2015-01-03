package com.alisovenko.algorithms.graphs.model;

import java.util.Iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation for graph. Uses adjacency list (map), can be used only for indirected graphs
 * 
 * <p>
 * Created: 23.12.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class Graph {

    private Map<Vertex, Set<Edge>> graph = new HashMap<Vertex, Set<Edge>>();
    
    private Set<Edge> edges = new HashSet<Edge>();

	// DFS global time
	public int time = 0;

    public Edge add(int from, int to, int weight) {
        Vertex fromV = Vertex.of(from);
        Vertex toV = Vertex.of(to);
        
        Edge edge = new Edge(fromV, toV, weight);
        if (graph.get(fromV) == null) {
            graph.put(fromV, new HashSet<Edge>());
        }
        if (graph.get(toV) == null) {
            graph.put(toV, new HashSet<Edge>());
        }
        // for undirected graph we need to put edge to both verteces
        graph.get(fromV).add(edge);
        graph.get(toV).add(edge);
        edges.add(edge);

        return edge;
    }
    
    public void add(Edge newEdge) {
        if (graph.get(newEdge.from) == null) {
            graph.put(newEdge.from, new HashSet<Edge>());
        }
        if (graph.get(newEdge.to) == null) {
            graph.put(newEdge.to, new HashSet<Edge>());
        }
        graph.get(newEdge.from).add(newEdge);
        graph.get(newEdge.to).add(newEdge);
        edges.add(newEdge);
    }

    public Set<Edge> getAdjacents(Vertex vertex) {
        Set<Edge> result = graph.get(vertex);
        if (result != null) {
            return new HashSet<Edge>(result);
        }
        return null;
    }
    
    public int size() {
        return graph.size();
    }
    
    public int edgesSize() {
        return edges.size();
    }
    
    public Iterator<Vertex> vertexesIterator() {
        return graph.keySet().iterator();
    }
    
    public Iterator<Edge> edgesIterator() {
        return edges.iterator();
    }
    
    public boolean contains(Vertex vertex) {
        return graph.containsKey(vertex);
    }
    
    public long length() {
        long result = 0;
        for (Edge edge : edges) {
            result += edge.weight;
        }
        return result;
    }

	public int increaseTime() {
		return ++time;
	}

	public void cleanColors() {
		for (Vertex vertex : graph.keySet()) {
			vertex.color = Vertex.Color.NONE;
		}
	}
}

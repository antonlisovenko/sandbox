package com.alisovenko.algorithms.graphs.model;

import java.util.HashMap;

/**
* User: alisovenko Date: 24.12.12
*/
public class Vertex implements Comparable<Vertex>{
	public static enum Color {
		NONE, GRAY, BLACK;
	}
    public final int id;
	public Color color = Color.NONE;
	public int discovered, finished;
    public Edge treeEdge;
    private static ThreadLocal<HashMap<Integer, Vertex>> cache = new ThreadLocal<HashMap<Integer, Vertex>>() {
        @Override
        protected HashMap<Integer, Vertex> initialValue() {
            return new HashMap<Integer, Vertex>();
        }
    };

    /** The key for this vertext (the minimum weighted edge, floting into the edge). Having maximum by default */
    private long key = Integer.MAX_VALUE;

    public static Vertex of(int id) {
        Vertex res = cache.get().get(id);
        if (res == null) {
            cache.get().put(id, res = new Vertex(id));
        }
        return res;
    }

    private Vertex(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", treeEdge=" + treeEdge +
                ", key=" + key +
                '}';
    }

    public void updateKey(long newValue) {
        this.key = newValue;
    }

    public long getKey() {
        return key;
    }

    public void setTreeEdge(Edge treeEdge) {
        this.treeEdge = treeEdge;
    }

    public Edge getTreeEdge() {
        return treeEdge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex vertex = (Vertex) o;

        if (id != vertex.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(Vertex o) {
        return key < o.key ? -1 : (key == o.key ? 0 : 1);
    }
}

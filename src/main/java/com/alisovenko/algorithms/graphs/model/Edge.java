package com.alisovenko.algorithms.graphs.model;

/**
* User: alisovenko Date: 24.12.12
*/
public class Edge implements Comparable<Edge>{
    public /*final*/ Vertex from;

    public /*final*/ Vertex to;

    public final int weight;

    /**
     * @param from
     * @param to
     * @param weight
     */
    public Edge(int from, int to, int weight) {
        super();
        this.from = Vertex.of(from);
        this.to = Vertex.of(to);
        this.weight = weight;
    }
    /**
     * @param from
     * @param to
     * @param weight
     */
    public Edge(Vertex from, Vertex to, int weight) {
        super();
        this.from =from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + from.id;
        result = prime * result + to.id;
        result = prime * result + weight;
        return result;
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (from != other.from)
            return false;
        if (to != other.to)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Edge o) {
        return weight < o.weight ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", from=" + from.id +
                ", to=" + to.id +
                '}';
    }
}

import edu.princeton.cs.algs4.*;

/**
 * <p/>
 * alisovenko 06.04.13
 */
public class SAP {
	private final Digraph d;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		this.d = new Digraph(G);
//		StdOut.print(d.toString());
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		int[] commonVertexInfo = resolveAncestor(v, w);

		return commonVertexInfo[1];
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		return resolveAncestor(v, w)[0];
	}


	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		return resolveAncestor(v, w)[1];
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		return resolveAncestor(v, w)[0];
	}

	// for unit testing of this class (such as the one below)
	public static void main(String[] args) {
		In in = new In(args[0]);

		Digraph G = new Digraph(in);

		int v = Integer.parseInt(args[1]);
		int w = Integer.parseInt(args[2]);

		SAP sap = new SAP(G);
//		while (!StdIn.isEmpty()) {
//			int v = StdIn.readInt();
//			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//		}
	}

	/**
	 * @return the information about the shortest ancestral path between two vertices
	 */
	private int[] resolveAncestor(int s, int o) {
		checkExists(s);
		checkExists(o);

		if (s == o) {
			return new int[]{s, 0};
		}

		// BFS on the second vertex
		BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(d, o);

		int ancestor = -1, minDistance = Integer.MAX_VALUE;

		// Performing dfs on graph from the first vertex. Supporting the variable with the shortest reachable ancestor
		Queue<Integer> q = new Queue<Integer>();
		boolean[] marked = new boolean[d.V()];
		int[] distTo = new int[d.V()];
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			// In case the distance is the smallest - we update the local variable
			if (wPaths.hasPathTo(v) && (wPaths.distTo(v) + distTo[v] < minDistance)) {
				minDistance = wPaths.distTo(v) + distTo[v];
				ancestor = v;
			}
			for (int w : d.adj(v)) {
				if (!marked[w]) {
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}

		return new int[]{ancestor, minDistance == Integer.MAX_VALUE ? - 1 : minDistance};
	}

	private void checkExists(int s) {
		if (s < 0 || s >= d.V()) {
			throw new IndexOutOfBoundsException("Vertex " + s + " is not inside graph");
		}
	}

	/**
	 * @return the information about the shortest ancestral path between two vertices
	 */
	private int[] resolveAncestor(Iterable<Integer> s, Iterable<Integer> o) {
		for (Integer integer : s) {
			checkExists(integer);
		}
		for (Integer integer : o) {
			checkExists(integer);
		}

		// BFS on the second set of vertices
		BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(d, o);

		int ancestor = -1, minDistance = Integer.MAX_VALUE;

		// Performing dfs on graph from the first set of vertices. Supporting the variable with the shortest reachable ancestor
		Queue<Integer> q = new Queue<Integer>();
		boolean[] marked = new boolean[d.V()];
		int[] distTo = new int[d.V()];
		for (int p : s) {
			marked[p] = true;
			distTo[p] = 0;
			q.enqueue(p);
		}

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : d.adj(v)) {
				if (!marked[w]) {
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);

					// In case the distance is the smallest - we update the local variable
					if (wPaths.hasPathTo(w) && (wPaths.distTo(w) + distTo[w] < minDistance)) {
						minDistance = wPaths.distTo(w) + distTo[w];
						ancestor = w;
					}
				}
			}
		}

		return new int[]{ancestor, minDistance};
	}
}

package com.alisovenko.algorithms.np;

import com.alisovenko.algorithms.graphs.PrimMSTTest;
import com.alisovenko.algorithms.graphs.Utils;
import com.alisovenko.algorithms.graphs.model.Edge;
import com.alisovenko.algorithms.graphs.model.Graph;
import com.alisovenko.algorithms.graphs.model.Vertex;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * alisovenko 03.02.13
 */
public class TwoSatProblem {
	public static void main(String[] args) throws IOException {
		System.out.println("1: " + check("data/2sat1.txt"));
		/*System.out.println("2: " + check("data/2sat2.txt"));
		System.out.println("3: " + check("data/2sat3.txt"));
		System.out.println("4: " + check("data/2sat4.txt"));
		System.out.println("5: " + check("data/2sat5.txt"));
		System.out.println("6: " + check("data/2sat6.txt"));
*/
	}

	private static boolean check(String s) throws IOException {
		// 1. Read graph
		Graph graph = readGraph(s);

		// 2. DFS
		Stack<Vertex> sorted = dfs(graph);

		// 3. Transpose
		transpose(graph);

		// 4. Clean all colors
		graph.cleanColors();

		// 5. DFS again in the revert topological order (thus taking vertexes from stack)
		Set<Set<Vertex>> sc = stronglyConnectedDfs(graph, sorted);
		System.out.println("Number of strongly connected components: " + sc.size());

		// 6. Check, that sc does not contain negated vertexes
		for (Set<Vertex> vertexes : sc) {
			for (Vertex vertex : vertexes) {
//				System.out.println(vertex.id + " " + (-vertex.id) + " " + vertexes.contains(Vertex.of(-vertex.id)));
				if (vertexes.contains(Vertex.of(-vertex.id))) {
					return false;
				}
			}
		}


		return true;
	}

	private static void transpose(Graph graph) {
		Iterator<Edge> iterator = graph.edgesIterator();

		while (iterator.hasNext()) {
			Edge next = iterator.next();
			Vertex v = next.to;
			next.to = next.from;
			next.from = v;
		}
	}

	public static Graph readGraph(String path) throws NumberFormatException, IOException {
		Graph gr = new Graph();

		TwoSatProblem test = new TwoSatProblem();
		InputStream input = test.getClass().getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		String line = reader.readLine();

		int numberOfNodes = Integer.valueOf(line.split(" ")[0]);
		int numberOfEdges = 0;
		if (line.split(" ").length > 1){
			numberOfEdges = Integer.valueOf(line.split(" ")[1]);
		}
		System.out.println("Expected " + numberOfNodes + " variables");
		Multiset<Vertex> multiset = HashMultiset.create();
		while ((line = reader.readLine()) != null) {
			String[] chunks = line.split(" ");
			addToGraph(gr, chunks, multiset);
		}
		System.out.println("In fact: variables: " + gr.size() + ", edges: " + gr.edgesSize());

		for (Multiset.Entry<Vertex> entry : multiset.entrySet()) {
			System.out.println("V: " + entry.getElement().id
					+ ": " + entry.getCount());
		}
		System.out.println(multiset.size());
		assert (gr.size() == numberOfNodes);
		assert (gr.edgesSize() == numberOfEdges);

		return gr;
	}

	private static void addToGraph(Graph gr, String[] chunks, Multiset<Vertex> multiset) {
		int first = Integer.parseInt(chunks[0]);
		int second = Integer.parseInt(chunks[1]);
		gr.add(new Edge(-first, second, 1));
		gr.add(new Edge(-second, first, 1));

		multiset.add(Vertex.of(first));
		multiset.add(Vertex.of(second));

	}

	private static Stack<Vertex> dfs(Graph gr) {
		Iterator<Vertex> vertexIterator = gr.vertexesIterator();
		Stack<Vertex> topologicallySorted = new Stack<Vertex>();
		
		while (vertexIterator.hasNext()) {
			Vertex next =  vertexIterator.next();
			if (next.color == Vertex.Color.NONE) {
				recursiveDfs(gr, next, topologicallySorted);
			}
		}

		assert topologicallySorted.size() == gr.size();

		return topologicallySorted;
	}
	private static void recursiveDfs(Graph gr, Vertex next, Stack<Vertex> topologicallySorted) {
		next.color = Vertex.Color.GRAY;
		next.discovered = gr.increaseTime();

		for (Edge edge : gr.getAdjacents(next)) {
			if (edge.to.color == Vertex.Color.NONE) {
				recursiveDfs(gr, edge.to, topologicallySorted);
			}
		}
		next.color = Vertex.Color.BLACK;
		next.finished = gr.increaseTime();
		topologicallySorted.push(next);
	}

	private static void recursiveDfs(Graph gr, Vertex next, Set<Vertex> sc) {
		next.color = Vertex.Color.GRAY;
		next.discovered = gr.increaseTime();

		for (Edge edge : gr.getAdjacents(next)) {
			if (edge.to.color == Vertex.Color.NONE) {
				recursiveDfs(gr, edge.to, sc);
			}
		}
		next.color = Vertex.Color.BLACK;
		next.finished = gr.increaseTime();
		sc.add(next);
	}


	private static Set<Set<Vertex>> stronglyConnectedDfs(Graph gr, Stack<Vertex> ordered) {

		Set<Set<Vertex>> result = new HashSet<Set<Vertex>>();
 		Vertex v;
int summ = 0;
		try {
			while ((v = ordered.pop()) != null) {
				Set<Vertex> sc = new HashSet<Vertex>();
				if (v.color == Vertex.Color.NONE) {
					recursiveDfs(gr, v, sc);
//				System.out.println("Size of sc: " + sc.size());
					summ+= sc.size();
				}
				result.add(sc);
			}
		} catch (EmptyStackException e) {
			// No-op.
		}
		System.out.println("Number of vertexes in all strong components: " + summ);

		return result;
	}

	private static int parseInt(String str) {
		return Integer.parseInt(str);
	}
}

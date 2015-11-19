import edu.princeton.cs.algs4.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * alisovenko 06.04.13
 */
public class WordNet {
	private final Map<String, Bag<Integer>> index = new HashMap<String, Bag<Integer>>();
	private final Map<Integer, String> dict = new HashMap<Integer, String>();

	private final SAP sap;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IllegalArgumentException {
		int size = readVertices(synsets);

		Digraph graph = readGraph(size, hypernyms);

		validateGraph(graph);

		sap = new SAP(graph);
	}

	private void validateGraph(Digraph graph) {
		// Validate there are no more than one root. For this we calculate the number of vertices that do not have adjacents (the "root" is not the vertex with no parent, but the vertex with no children)
//		Digraph g = graph.reverse();
		Digraph g = graph;
		short cnt = 0;
		for (int i = 0; i < g.V(); i++) {
			if (!g.adj(i).iterator().hasNext()) {
				cnt++;
			}
		}

		if (cnt > 1) {
			throw new IllegalArgumentException("Graph has " + cnt + " roots");
		}

		// Validate graph does not contain cycles
		KosarajuSharirSCC scc = new KosarajuSharirSCC(graph);
		if (scc.count() < graph.V()) {
			throw new IllegalArgumentException("Graph contains " + (graph.V() - scc.count()) + " cycles");
		}
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return index.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return index.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) throws IllegalArgumentException {
		checkWord(nounA);
		checkWord(nounB);

		return sap.length(index.get(nounA), index.get(nounB));
	}


	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) throws IllegalArgumentException {
		checkWord(nounA);
		checkWord(nounB);

		int ancestor = sap.ancestor(index.get(nounA), index.get(nounB));

		if (ancestor >= 0) {
			return dict.get(ancestor);
		}

		return "<no found>";
	}

	// for unit testing of this class
	public static void main(String[] args) {
		WordNet wordNet = new WordNet(args[0], args[1]);

		String v = args[2];
		String w = args[3];

		int length = wordNet.distance(v, w);
		String ancestor = wordNet.sap(v, w);
		StdOut.printf("distance = %d, sap = %s\n", length, ancestor);

	}

	private Digraph readGraph(int size, String hypernyms) {
		In in = new In(hypernyms);

		Digraph graph = new Digraph(size);

		if (!in.exists()) {
			throw new IllegalArgumentException(String.format("File '%s' does not exist", hypernyms));
		}

		while (in.hasNextLine()) {
			String[] splitted = in.readLine().split(",");
			for (int i = 1; i < splitted.length; i++) {
				graph.addEdge(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[i]));
			}
		}
		return graph;
	}

	/**
	 * @return the number of vertices in graph. Reads all vertices and their names to inner dictionary
	 */
	private int readVertices(String synsets) {
		In in = new In(synsets);
		int cnt = 0;

		if (!in.exists()) {
			throw new IllegalArgumentException(String.format("File '%s' does not exist", synsets));
		}

		while (in.hasNextLine()) {
			cnt++;
			String[] splitted = in.readLine().split(",");

			// We split the synset to individual words
			String[] words = splitted[1].trim().split(" ");

			for (String word : words) {
				Bag<Integer> bag = index.get(word);
				if (bag == null) {
					bag = new Bag<Integer>();
				}
				bag.add(Integer.parseInt(splitted[0]));
				index.put(word, bag);
			}
			dict.put(Integer.parseInt(splitted[0]), splitted[1].trim());
		}
		return cnt;
	}

	private void checkWord(String noun) {
		if (!isNoun(noun)) {
			throw new IllegalArgumentException("Word " + noun + " is not a noun");
		}
	}

}

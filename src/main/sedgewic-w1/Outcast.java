import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that allows to find the most non-synonym word among the given ones.
 * <p/>
 * alisovenko 06.04.13
 */
public class Outcast {
	/**
	 * Wordnet. *
	 */
	private final WordNet wordNet;

	/**
	 * Constructor takes a WordNet object.
	 *
	 * @param wordnet wordnet
	 */
	public Outcast(final WordNet wordnet) {
		this.wordNet = wordnet;
	}

	/**
	 * Given an array of WordNet nouns, return an outcast.
	 *
	 * @param nouns
	 * @return the outcast word
	 */
	public String outcast(final String[] nouns) {
		// We use simple caching for those distances that have already
		// been checked (but in reverse order)
		Map<String, Integer> cached = new HashMap<String, Integer>();

		int[] distances = new int[nouns.length];

		for (int i = 0; i < nouns.length; i++) {
			for (String s : nouns) {
				Integer cnt = cached.get(sort(nouns[i], s));
				if (cnt != null && cnt > 0) {
					distances[i] += cnt;
				} else {
					int distance = wordNet.distance(nouns[i], s);
					distances[i] += distance;
					cached.put(sort(nouns[i], s), distance);
				}
			}
		}

		// Now we take the maximum distance and the matching noun
		int max = 0, idx = -1;
		for (int i = 0; i < distances.length; i++) {
			if (distances[i] > max) {
				max = distances[i];
				idx = i;
			}
		}

		return nouns[idx];
	}

	private String sort(String first, String second) {
		return first.compareTo(second) > 0 ? first + second
				: second + first;
	}

	// for unit testing of this class (such as the one below)
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			String[] nouns = In.readStrings(args[t]);
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}

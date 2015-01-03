/**
 * <p/>
 * alisovenko 29.05.13
 */
public class BurrowsWheeler {
	private static final int R = 256; // Radix of a byte.
	// apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
	public static void encode() {
		String s = BinaryStdIn.readString();

		CircularSuffixArray circularSuffixArray = new CircularSuffixArray(s);

		// Looking for the index of first string (not shifted)
		for (int i = 0; i < circularSuffixArray.length(); i++) {
			if (circularSuffixArray.index(i) == 0) {
				BinaryStdOut.write(i);
				break;
			}
		}

		for (int i = 0; i < s.length(); i++) {
			BinaryStdOut.write(getLatestChar(s, circularSuffixArray.index(i)));
		}

		BinaryStdOut.flush();
	}

	private static char getLatestChar(String s, int index) {
		int n = s.length();
		if (index == 0) {
			return s.charAt(s.length() - 1);
		}
		return s.charAt(index - 1);
	}

	// apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
	public static void decode() {
		int first = BinaryStdIn.readInt();
		String t = BinaryStdIn.readString();
		int n = t.length();
		int[] count = new int[R + 1], next = new int[n];
		for (int i = 0; i < n; i++)
			count[t.charAt(i) + 1]++;
		for (int i = 1; i < R + 1; i++)
			count[i] += count[i - 1];
		for (int i = 0; i < n; i++)
			next[count[t.charAt(i)]++] = i;
		for (int i = next[first], c = 0; c < n; i = next[i], c++)
			BinaryStdOut.write(t.charAt(i));
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply Burrows-Wheeler encoding
	// if args[0] is '+', apply Burrows-Wheeler decoding
	public static void main(String[] args) {
		if (args[0].equals("-"))
			encode();
		else if (args[0].equals("+"))
			decode();
		else
			throw new RuntimeException("Illegal command line argument");
	}
}

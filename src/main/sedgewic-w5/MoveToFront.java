import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p/>
 * alisovenko 29.05.13
 */
public class MoveToFront {
	private static LinkedList<Character> alphabet = new LinkedList<Character>();

	private static void init(){
		for (int i = 0; i < 255; i++) {
			alphabet.add(new Character((char) i));
		}
	}

	// apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode() {
		init();
		String s = BinaryStdIn.readString();

		for (char c : s.toCharArray()) {
			enc(c);
		}

		BinaryStdOut.flush();
	}

	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode() {
		init();
		String s = BinaryStdIn.readString();

		for (char c : s.toCharArray()) {
			dec(c);
		}

		BinaryStdOut.flush();
	}

	private static void dec(char c) {
		int cnt = 0;
		int idx = (int)c;
		Character found = null;

		for (Iterator<Character> iterator = alphabet.iterator(); iterator.hasNext(); ) {
			Character next = iterator.next();
			if (cnt == idx) {
				BinaryStdOut.write(next.charValue());
				found = next;
				iterator.remove();

				break;
			}
			cnt++;
		}

		if (found != null) {
			alphabet.add(0, found);
		}
	}

	private static void enc(char c) {
		byte cnt = 0;
		Character found = null;
		for (Iterator<Character> iterator = alphabet.iterator(); iterator.hasNext(); ) {
			Character next = iterator.next();
			if (next.charValue() == c) {
				BinaryStdOut.write(cnt);
				found = next;
				iterator.remove();

				break;
			}
			cnt++;
		}

		if (found != null) {
			alphabet.add(0, found);
		}
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		if (args[0].equals("-"))
			encode();
		else if (args[0].equals("+"))
			decode();
		else
			throw new RuntimeException("Illegal command line argument");
	}
}

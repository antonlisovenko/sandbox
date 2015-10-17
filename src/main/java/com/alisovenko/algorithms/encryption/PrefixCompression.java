package com.alisovenko.algorithms.encryption;

import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * @author alisovenko 26.04.15
 */
public abstract class PrefixCompression {
    private static int R = 'я' + 1; // all letters up to 'я'

    // Trie node
    private static class Node implements Comparable<Node> {
        private Node left;
        private Node right;
        private final char ch; // '0' for non-leaf node
        private int freq;

        private Node(Node left, Node right, char ch, int freq) {
            this.left = left;
            this.right = right;
            this.ch = ch;
            this.freq = freq;
        }

        public boolean isLeaf() {
            return ch != '0';
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(freq, o.freq);
        }

        @Override
        public String toString() {
            return "[" + ch + ", " + freq + "]";
        }
    }

    // Huffman implementation of building Trie for symbols
    private static class Huffman extends PrefixCompression {
        @Override
        Node buildTrie(int[] freq) {
            PriorityQueue<Node> pq = new PriorityQueue<>();

            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > 0) {
                    pq.add(new Node(null, null, (char) i, freq[i]));
                }
            }

            while (pq.size() > 1) {
                Node n1 = pq.poll();
                Node n2 = pq.poll();

                pq.add(new Node(n1, n2, '0', n1.freq + n2.freq));
            }
            return pq.poll();
        }
    }

    // Fano implementation. Algorithm: sort all symbols by frequency (use positions array for this so that not loose
    // initial character indices), then recursively find the left and right sets of symbols so that they had almost
    // equal total frequencies. To do this increase left frequency and decrease the right one, as soon as left one
    // exceeds the right one - recurse for the left and right parts
    private static class Fano extends PrefixCompression {
        @Override
        Node buildTrie(int[] freq) {
            Integer[] positions = new Integer[freq.length];
            for (int i = 0; i < freq.length; i++) {
                positions[i] = i;
            }
            Arrays.sort(positions, (o1, o2) -> Integer.compare(freq[o1], freq[o2]));
            return buildTrie(freq, positions, 0, freq.length, Arrays.stream(freq).sum());
        }

        Node buildTrie(int[] freq, Integer[] positions, int from, int to, int sum) {
            if (from > to || sum == 0) {
                return null;
            }
            if (from == to) {
                return new Node(null, null, (char) positions[from].intValue(), 0);
            }
            int left = 0, right = sum;
            int i;
            int firstNotEmpty = -1;
            boolean br = false;
            for (i = from; i < to; i++) {
                if (freq[positions[i]] > 0 && firstNotEmpty == -1) {
                    firstNotEmpty = i;
                }
                left += freq[positions[i]];
                right -= freq[positions[i]];
                if (left >= right) {
                    br = true;
                    break;
                }
            }
            if (!br) i--; // magic for case if i == to (last element)
//            System.out.printf("from: '%s' (%d), i: '%s' (%d), left: %d, right: %d\n", (char)positions[from].intValue(), positions[from], (char)positions[i].intValue(), positions[i], left, right);

            Node leftNode = buildTrie(freq, positions, firstNotEmpty, i, left);
            Node rightNode = buildTrie(freq, positions, i + 1, to, right);
            return new Node(leftNode, rightNode, '0', 0); // frequency is not used for Fano
        }
    }

    abstract Node buildTrie(int[] freq);

    public void test(String str) {
        System.out.println("\n### " + this.getClass().getSimpleName());

        Node trie = buildTrie(calculateFrequencies(str));
        String[] dict = new String[R];
        fillDict(trie, dict, "");

        BitSet encoded = new BitSet();
        int length = encode(dict, str, encoded);

        printDict(dict);
        printEncoding(encoded, length);

        String decoded = decode(trie, encoded, length);

        System.out.printf("Decoded:\n%s\n", decoded);
    }

    private static void printEncoding(BitSet encoded, int length) {
        System.out.println("Encoded:");
        for (int i = 0; i < length; i++) {
            if (encoded.get(i)) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    private static void printDict(String[] dict) {
        System.out.println("Codewords:");
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != null) {
                System.out.printf("%s -> %s\n", (char) i, dict[i]);
            }
        }
    }

    private void fillDict(Node node, String[] dict, String path) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            dict[node.ch] = path;
            return;
        }
        fillDict(node.left, dict, path + "0");
        fillDict(node.right, dict, path + "1");
    }

    private String decode(Node trie, BitSet encoded, int length) {
        Node node = trie;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (encoded.get(i)) {
                node = node.right;
            } else {
                node = node.left;
            }
            if (node.isLeaf()) {
                // found new character, reset the node reference to root
                sb.append(node.ch);
                node = trie;
            }
        }
        return sb.toString();
    }

    private int encode(String[] dict, String str, BitSet result) {
        int pos = 0;
        for (final char c : str.toCharArray()) {
            int idx = c;
            if (idx < 0 || idx >= dict.length) {
                throw new IllegalArgumentException("String " + str + " contains wrong characters!");
            }
            String s = dict[idx];
            if (s == null) {
                System.out.println("No codeword for " + (char) idx);
            }
            for (char c1 : s.toCharArray()) {
                if (c1 == '1') {
                    result.set(pos);
                }
                pos++;
            }
        }
        return pos;
    }

    private int[] calculateFrequencies(String str) {
        int[] a = new int[R];
        for (final char c : str.toCharArray()) {
            int idx = c;
            assert idx > 0 && idx < R : "Wrong character " + c;

            a[idx]++;
        }
        return a;
    }

    public static void main(String[] args) {
        new Huffman().test("Лисовенко Антон Витальевич, Системы передачи информации, Кудрявцев  Григорий Михайлович");
        new Fano().test("Лисовенко Антон Витальевич, Системы передачи информации, Кудрявцев  Григорий Михайлович");
    }

}

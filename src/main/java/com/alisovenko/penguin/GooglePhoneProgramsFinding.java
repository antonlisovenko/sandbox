package com.alisovenko.penguin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alisovenko 22.09.14
 */
public class GooglePhoneProgramsFinding {
    private static class Dictionary {
        Node root = new Node();

        public boolean contains(String str) {
            return true;
        }

        public void findByPrefix(String prefix) {
            // find the node for prefix
            Node current = root;
            for (final char c : prefix.toCharArray()) {
                Node next = current.values.get(c);

                if (next == null) {
                    System.out.println("Failed");
                    return;
                }
                current = next;
            }

            // now iterate through all descendants for the node and construct the words
            dfs(current);
        }

        public void dfs(Node n) {
            if (n == null || n.values.isEmpty()) {
                System.out.println();
                return;
            }
            for (final Map.Entry<Character, Node> entry : n.values.entrySet()) {
                System.out.print(entry.getKey());
                dfs(entry.getValue());
            }
        }

        public void add(String s) {
            Node current = root;
            for (final char c : s.toCharArray()) {
                Node n;
                if (current.values.containsKey(c)) {
                    n = current.values.get(c);
                }
                else {
                    n = new Node();
                }
                current.values.put(c, n);
                current = n;
            }
        }
    }

    private static class Node {
        Map<Character, Node> values = new HashMap<>();
    }

    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        dict.add("angry birds");
        dict.add("angry cats");
        dict.add("angry tigers");

        dict.findByPrefix("angry");
    }
}

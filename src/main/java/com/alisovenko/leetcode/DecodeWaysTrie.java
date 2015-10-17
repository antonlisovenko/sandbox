package com.alisovenko.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author alisovenko 26.01.15
 */
public class DecodeWaysTrie {
    private static class Trie {
        Node root;
        int R;

        private static class Node {
            char ch;
            Node[] children;

            public Node(int R, char ch) {
                this.children = new Node[R];
                this.ch = ch;
            }

            public Node(int R) {
                this.children = new Node[R];
            }

            public Node getChild(char c) {
                return children[charToInt(c)];
            }

            public void setChild(Node n) {
                children[charToInt(n.ch)] = n;
            }

            private void add(String str) {
                if (str.length() == 0) {
                    return;
                }
                Node n = getChild(str.charAt(0));
                if (n == null) {
                    n = new Node(children.length, str.charAt(0));
                }
                setChild(n);
                n.add(str.substring(1));
            }

            private static int charToInt(char c) {
                return c - '1' + 1;
            }
        }

        public Trie(int R) {
            this.R = R;
            root = new Node(R);
        }

        public void add(String str) {
            root.add(str);
        }

        public Set<Node> search(Set<Node> nodes, char p) {
            Set<Node> result = new HashSet<>();
            for (Node n : nodes) {
                if (n.getChild(p) != null) result.add(n.getChild(p));
            }
            if (root.getChild(p) != null) result.add(root.getChild(p));
            return result;
        }
    }

    public int numDecodings(String s) {
        Trie trie = new Trie(10);
        int c = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            trie.add(String.valueOf(c++));
        }

        Set<Trie.Node> curNodes = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            curNodes = trie.search(curNodes, s.charAt(i));
            result += curNodes.size();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWaysTrie().numDecodings("0"));
    }
}

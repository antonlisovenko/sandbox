package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         6/24/16.
 */
public class WordSearch2 {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        boolean terminal;

        // todo iterator
        public Node next(char c) {
            return children.get(c);
        }
    }
    private static class Trie {
        Node root;
        public void add(String word) {
            root = add(root, word, 0);
        }
        private Node add(Node n, String word, int idx) {
            Node result = n == null ? new Node() : n;
            if (idx == word.length()) {
                result.terminal = true;
                return result;
            }
            char k = word.charAt(idx);
            result.children.put(k, add(result.children.get(k), word, idx + 1));
            return result;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || words == null || words.length == 0) return Collections.emptyList();

        int r = board.length, c = board[0].length;

        boolean[][] path = new boolean[r][c];
        List<String> ans = new ArrayList<>();

        Trie trie = new Trie();
        for (String w: words) trie.add(w);

        for (int i = 0; i < r; i++) {
            for (int p = 0; p < c; p++) {
                dfs(board, i, p, path, trie.root, ans, new StringBuilder());
            }
        }
        return ans;
    }

    private void dfs(char[][] board, int i, int p, boolean[][] path, Node node, List<String> ans, StringBuilder sb) {
        if (i < 0 || i >= board.length || p < 0 || p >= board[0].length || path[i][p] || node.next(board[i][p]) == null) return;

        path[i][p] = true;
        sb.append(board[i][p]);

        if (node.next(board[i][p]).terminal) {
            ans.add(sb.toString());
            node.next(board[i][p]).terminal = false;
        }
        node = node.next(board[i][p]);

        dfs(board, i - 1, p, path, node, ans, sb);
        dfs(board, i, p - 1, path, node, ans, sb);
        dfs(board, i + 1, p, path, node, ans, sb);
        dfs(board, i, p + 1, path, node, ans, sb);

        path[i][p] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new WordSearch2().findWords(new char[][]{{'a', 'b'}, {'a', 'a'}}, new String[]
                {"aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba"}));
    }
}

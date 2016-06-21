package com.alisovenko.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author alisovenko
 *         6/18/16.
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) return Collections.emptyList();

        Set<List<Integer>> ans = new HashSet<>();
        Set<Node> nodes = new TreeSet<>();
        Integer empty = null;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (w.equals("")) empty = i;

            String leftPrefix = checkRightPalindrome(w);
            if (leftPrefix != null) nodes.add(new Node(i, leftPrefix, true));

            String rightPrefix = checkLeftPalindrome(w);
            if (rightPrefix != null) nodes.add(new Node(i, rightPrefix, false));

            if (w.length() > 1) {
                nodes.add(new Node(i, w.substring(0, w.length() - 1), true));
                nodes.add(new Node(i, reversed(w.substring(1)), false));
            }
            nodes.add(new Node(i, w, true));
            nodes.add(new Node(i, reversed(w), false));
        }

        System.out.println(nodes);
        Node current = null, last = null;
        for (Node n: nodes) {
            if (current == null) {
                current = n;
                continue;
            }
            last = current;
            current = n;

            if (last.val.equals(current.val) && last.fromLeft != current.fromLeft && last.idx != current.idx && isPalindrome(last, current, words))
                if (last.fromLeft)  ans.add(Arrays.asList(last.idx, current.idx));
                else                ans.add(Arrays.asList(current.idx, last.idx));

            if (empty != null && current.val.equals(reversed(current.val)) && current.idx != empty)
                if (current.fromLeft)  ans.add(Arrays.asList(current.idx, empty));
                else                ans.add(Arrays.asList(empty, current.idx));
        }
        return ans.stream().collect(Collectors.toList());
    }

    private boolean isPalindrome(Node last, Node current, String[] words) {
        String s = last.fromLeft ? words[last.idx] + words[current.idx] : words[current.idx] + words[last.idx];
        return s.equals(reversed(s));
    }

    private String checkRightPalindrome(String s) {
        for (int i = 1; i < s.length() - 1; i++) {
            int p = i, l = s.length() - 1;
            while (s.charAt(p) == s.charAt(l) && p < l) {
                p++;
                l--;
            }
            if (p >= l) return s.substring(0, i + 1);
        }
        return null;
    }

    private String checkLeftPalindrome(String s) {
        for (int i = s.length() - 2; i > 0; i--) {
            int p = i, l = 0;
            while (s.charAt(p) == s.charAt(l) && p > l) {
                p--;
                l++;
            }
            if (p <= l) return s.substring(i + 1);
        }
        return null;
    }

    private String reversed(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static class Node implements Comparable<Node>{
        private int idx;
        private String val;
        private boolean fromLeft;

        public Node (int idx, String val, boolean fromLeft) {
            this.idx = idx;
            this.val = val;
            this.fromLeft = fromLeft;
        }

        public int compareTo(Node other) {
            int c = val.compareTo(other.val);
            if (c != 0) return c;
            c = Integer.compare(idx, other.idx);
            if (c!= 0) return c;
            return Boolean.compare(fromLeft, other.fromLeft);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", val='" + val + '\'' +
                    ", fromLeft=" + fromLeft +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePairs().palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}

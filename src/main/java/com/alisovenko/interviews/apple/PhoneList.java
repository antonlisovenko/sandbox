package com.alisovenko.interviews.apple;

import java.util.Scanner;

/**
 * Implementation details:
 * <p>
 * Prefix matching is the best done using tries. This allows for early discovering of prefixes in the input data.
 * <p>
 * Algorithm complexity is O(n) where n is the total number of phones digits in each test case.
 * <p>
 * Memory consumption: technical spec (~1GB of memory, 10_000 phones numbers per test case, 10 - based radix) allows to
 * implement trie node children as the array indexed with digit. In case radix was larger (e.g. Unicode alphabite) this
 * would waste enormous memory, dynamically extended hash tables would fit better
 *
 * @author alisovenko 20.06.15
 */
public class PhoneList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCasesCount = scanner.nextInt();

        for (int i = 0; i < testCasesCount; i++) {
            runTestCase(scanner);
        }
    }

    private static void runTestCase(Scanner scanner) {
        int phonesCount = scanner.nextInt();

        TrieNode root = new TrieNode();

        boolean bad = false;
        for (int i = 0; i < phonesCount; i++) {
            String nextNumber = scanner.next();
            if (bad) {
                continue;
            }

            if (!root.addChild(nextNumber)) {
                // Found inconsistent result, no need to proceed
                System.out.println("NO");
                bad = true;
            }
        }
        if (!bad) {
        System.out.println("YES");

        }
    }

    /**
     * Trie implementation for numbers with base 10. Has the contract to contain terminal nodes only in leaves, forbids
     * addition of numbers breaking this contract. This allows to support the numbers that can not be prefix of the
     * other one.
     */
    private static class TrieNode {
        private TrieNode[] children = new TrieNode[10];

        private boolean terminal;

        /**
         * Adds the string representation of number to trie
         *
         * @param str string representation of number
         * @return true if str was added succussfully. False in case current trie node is not terminal or terminal node
         * was found during addition of string
         */
        public boolean addChild(String str) {
            return addChild(str, 0);
        }

        /**
         * Adds the substring of {@code str} starting from {@code pos} symbol to current trie node.
         *
         * @param str string representation of number which suffix will be added to current trie node
         * @param pos the starting symbol (including) for suffix for str parameter
         * @return true if str was added succussfully. False in case current trie node is not terminal or terminal node
         * was found during addition of string
         */
        private boolean addChild(String str, int pos) {
            // base case 1: we came to the end of string
            if (pos == str.length()) {
                if (checkHasChildren()) {
                    // .. but there are more descendants, so current string
                    // is prefix of another one
                    return false;
                }
                // current trie node is new terminal leaf
                terminal = true;
                return true;
            }
//            System.out.println("!" + str.charAt(pos));
            int nextNumber = str.charAt(pos) - '0';

            TrieNode child = children[nextNumber];
            if (child == null) {
                children[nextNumber] = new TrieNode();
                child = children[nextNumber];
            }

            // base case 2: according to tech spec all numbers are unique - so we came to some string that will be
            // the prefix of current one
            if (child.terminal) {
                return false;
            }
            return child.addChild(str, pos + 1);
        }

        private boolean checkHasChildren() {
            for (final TrieNode child : children) {
                if (child != null) {
                    return true;
                }
            }
            return false;
        }
    }
}

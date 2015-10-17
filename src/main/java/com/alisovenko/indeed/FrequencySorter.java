package com.alisovenko.indeed;

import java.util.*;

/**
 * @author alisovenko 10.01.15
 */
public class FrequencySorter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();

        char[] chars = input.toCharArray();

        Map<Character, Integer> multiset = new HashMap<>();

        for (final Character ch : chars) {
            if (multiset.containsKey(ch)) {
                multiset.put(ch, multiset.get(ch) + 1);
            }
            else {
                multiset.put(ch, 1);
            }
        }

        List<CharWrapper> wrappers = new ArrayList<>();

        for (final Map.Entry<Character, Integer> entry : multiset.entrySet()) {
            wrappers.add(new CharWrapper(entry.getKey(), entry.getValue()));
        }

        Collections.sort(wrappers);

        for (final CharWrapper wrapper : wrappers) {
            System.out.println(wrapper.ch);
        }
    }

    private static class CharWrapper implements Comparable<CharWrapper>{
        final char ch;
        final int priority;

        private CharWrapper(char ch, int priority) {
            this.ch = ch;
            this.priority = priority;
        }

        @Override
        public int compareTo(CharWrapper o) {
            int compare = Integer.compare(o.priority, this.priority);
            if (compare == 0) {
                return Character.compare(ch, o.ch);
            }
            return compare;
        }
    }
}

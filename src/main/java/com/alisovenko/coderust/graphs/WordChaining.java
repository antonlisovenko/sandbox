package com.alisovenko.coderust.graphs;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author alisovenko 04.02.15
 */
public class WordChaining {
    public static void main(String[] args) {
        String[] orderedByFirst = Arrays.copyOf(args, args.length);
        String[] orderedByLast = Arrays.copyOf(args, args.length);

        Arrays.sort(orderedByFirst);
        Arrays.sort(orderedByLast, (o1, o2) -> Integer.compare(o1.charAt(o1.length() - 1), o2.charAt(o2.length() - 1)));

        for (int i = 0; i < args.length; i++) {
            String s = orderedByLast[i];
            if (orderedByFirst[i].charAt(0) != s.charAt(s.length() - 1)) {
                System.out.println("NO!");
                return;
            }
        }
    }

}

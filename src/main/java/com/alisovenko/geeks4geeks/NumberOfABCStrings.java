package com.alisovenko.geeks4geeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko 05.02.15
 */
public class NumberOfABCStrings {
    private static class Sequence {
        private final String val;
        private boolean hasB;
        private boolean hasDoubleC;
        public Sequence (String s, boolean b, boolean c) {
            this.val = s;
            this.hasB = b;
            this.hasDoubleC = c;
        }
        public Sequence(Sequence other, char c) {
            this(other, c, other.hasB, other.hasDoubleC);
        }

        public Sequence(Sequence other, char p, boolean b, boolean c) {
            this.val = other.val + p;
            this.hasB = b;
            this.hasDoubleC = c;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public static List<Sequence> findSequences(int n) {
        if (n == 0) {
            return Collections.singletonList(new Sequence("", false, false));
        }
        List<Sequence> sequences = findSequences(n - 1);

        List<Sequence> clones = new ArrayList<>();

        for (Sequence seq : sequences) {
            clones.addAll(createClones(seq));
        }

        return clones;
    }

    private static List<Sequence> createClones(Sequence seq){
        List<Sequence> result = new ArrayList<>();
        result.add(new Sequence(seq, 'a'));

        if (!seq.hasB) {
            result.add(new Sequence(seq, 'b', true, seq.hasDoubleC));
        }
        if (!(seq.hasDoubleC && seq.val.endsWith("c"))) {
            result.add(new Sequence(seq, 'c', seq.hasB, seq.val.endsWith("c")));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("1:");
        System.out.println(findSequences(1));

        System.out.println("2:");
        System.out.println(findSequences(2));

        System.out.println("3:");
        System.out.println(findSequences(3));

        System.out.println("4:");
        List<Sequence> sequences = findSequences(4);
        System.out.println(sequences.size());
    }
}

package com.alisovenko.algorithms.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

/**
 * Test for greedy algorithm of scheduling threads based on their time and weight. The main principle: the bigger is the
 * weight and less is its lenght - the earlier it should happen. There are 2 variants: using division and difference.
 * 
 * This is from coursera course https://class.coursera.org/algo2-2012-001
 * <p>
 * Created: 21.12.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class GreedySchedulingTest {
    public static void main(String[] args) throws IOException {
        GreedySchedulingTest test = new GreedySchedulingTest();
        InputStream input = test.getClass().getResourceAsStream("jobs.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        TreeSet<Unit> lines = new TreeSet<Unit>();
        String line;
        int number = Integer.valueOf(reader.readLine());
        while ((line = reader.readLine()) != null) {
            lines.add(test.new Unit(line));
        }

        System.out.println("Size: " + lines.size());

        long cumulativeSum = 0;
        int cumulativeTime = 0;

        for (Unit unit : lines) {
            cumulativeTime += unit.length;
            cumulativeSum += cumulativeTime * unit.weight;
        }

        System.out.println("1: " + cumulativeSum + ", " + cumulativeTime);

        // 2
        input = test.getClass().getResourceAsStream("jobs.txt");
        reader = new BufferedReader(new InputStreamReader(input));
        lines = new TreeSet<Unit>();
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            lines.add(test.new Unit2(line));
        }

        cumulativeSum = 0;
        cumulativeTime = 0;

        for (Unit unit : lines) {
            cumulativeTime += unit.length;
            cumulativeSum += cumulativeTime * unit.weight;
        }

        System.out.println("2: " + cumulativeSum + ", " + cumulativeTime);

    }

    private class Unit implements Comparable<Unit> {
        int weight;

        int length;

        /**
         * 
         */
        public Unit(String pair) {
            weight = Integer.valueOf(pair.split(" ")[0]);
            length = Integer.valueOf(pair.split(" ")[1]);
        }

        /**
         * @see Comparable#compareTo(Object)
         */
        public int compareTo(Unit o) {
            int thisDifference = ratio();
            int otherDifference = o.ratio();

            if (thisDifference == otherDifference) {
                return weight > o.weight ? -1 : 1;
            }
            return thisDifference > otherDifference ? -1 : 1;
        }

        int ratio() {
            return weight - length;
        }

        double ratio2() {
            return (double) weight / length;
        }

        /**
         * @see Object#toString()
         */
        @Override
        public String toString() {
            return "Unit [weight=" + weight + ", length=" + length + "]";
        }
    }

    private class Unit2 extends Unit {

        /**
         * @param pair
         */
        public Unit2(String pair) {
            super(pair);
        }

        /**
         * @see com.alisovenko.algorithms.others.GreedySchedulingTest.Unit#compareTo(com.alisovenko.algorithms.others.GreedySchedulingTest.Unit)
         */
        @Override
        public int compareTo(Unit o) {
            double thisDifference = ratio2();
            double otherDifference = o.ratio2();

            if (thisDifference == otherDifference) {
                return weight > o.weight ? -1 : 1;
            }
            return thisDifference > otherDifference ? -1 : 1;
        }
    }

}

package com.alisovenko.algorithms.divideConquer;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import org.junit.Test;

/**
 * Test for Fork-join framework (jdk7). Shows, that FJ is useful with big data whith some non-trivial operations with it
 * (simple n * n is very good when used linearly) and FJ does not "win" for this cases..
 * 
 * <p>
 * Created: 20.04.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class ForkJoinTest {
    private static final Integer THREASOLD = 500000;

    @Test
    public void test() {
        int[] array = generate(50000000);

        // Fork join
        Task task = new Task(array, 0, array.length - 1);

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("<<<<< Fork join >>>>>>");
        long t = System.currentTimeMillis();
        long result = pool.invoke(task);
        System.out.println("<<<<< Results: " + (System.currentTimeMillis() - t) + " ms >>>>>");

        // Consequental
        System.out.println("<<<<< Consequental >>>>>>");
        t = System.currentTimeMillis();
        long result2 = computeConsequently(array, 0, array.length - 1);
        System.out.println("<<<<< Results: " + (System.currentTimeMillis() - t) + " ms >>>>>");

        assertEquals(result, result2);

        System.out.println(result);
    }

    /**
     * TBD
     * @return
     */
    private int[] generate(int number) {
        Random rand = new Random(10);
        int[] result = new int[number];

        for (int i = 0; i < number; i++) {
            result[i] = rand.nextInt();
        }
        return result;
    }

    /**
     * Recursive task
     * 
     * <p>Created: Apr 20, 2012</p>
     * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
     */
    private static class Task extends RecursiveTask<Long> {
        private int[] array;

        private int start;

        private int end;

        public Task(int[] array, int start, int end) {
            super();
            this.array = array;
            this.start = start;
            this.end = end;
        }

        /**
         * @see java.util.concurrent.RecursiveTask#compute()
         */
        @Override
        protected Long compute() {
            if ((end - start) < THREASOLD) {
                return computeConsequently(array, start, end);
            }
            int mid = (start + end) / 2;
            Task left = new Task(array, start, mid);
            Task right = new Task(array, mid, end);

            right.fork();

            return left.invoke() + right.join();
        }
    }

    private static Long computeConsequently(int[] array, int start, int stop) {
        long result = 0;
        for (int i = start; i < stop; i++) {
            int p = doSomeAction(array[i]);
            // System.out.printf("Index: %s, element %s, result: %s\n", i, array[i], p);
            result += p;
        }
        return result;
    }

    private static int doSomeAction(int i) {
        return i * i / 2 * 3 / 7 / 3 * 9 * 2 / 4;
    }
}

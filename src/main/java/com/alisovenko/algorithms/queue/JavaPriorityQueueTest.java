package com.alisovenko.algorithms.queue;

import java.util.PriorityQueue;

/**
 * @author alisovenko
 *         2/12/16.
 */
public class JavaPriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(9);
        queue.offer(8);
        queue.offer(3);
        queue.offer(1);
    }
}

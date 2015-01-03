package com.alisovenko.cracking.the.interview._3_stacks_queues;

import java.util.Stack;

/**
 * @author alisovenko 21.08.14
 */
public class _3_5 {
    public static class MyQueue {
        private final Stack<Integer> first = new Stack<>();
        private final Stack<Integer> second = new Stack<>();

        public void enqueue(int i) {
            first.push(i);
        }

        public int dequeue() {
            Integer next;
            while ((next = first.pop()) != null) {
                second.push(next);
            }

            return second.pop();
        }
    }
}

package com.alisovenko.cracking.the.interview._3_stacks_queues;

/**
 * @author alisovenko 18.08.14
 */
public class _3_1 {
    public static class Stack {
        private final int[] arr;
        private final int start;
        private final int finish;
        private volatile int idx;
        public Stack (int[] arr, int pos, int end) {
            this.arr = arr;
            this.start = pos;
            this.finish = end;
            this.idx = start;
        }

        public void push(int i) {
            if (idx == finish) {
                throw new IllegalStateException();
            }
            arr[idx++]=i;
        }
        public int pop() {
            if (idx == start) {
                throw new RuntimeException();
            }
            return arr[idx--];
        }
    }
}

package com.alisovenko.algorithms.queue;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

/**
 * Test for heap-based priority queue.
 * 
 * <p>
 * Created: 08.05.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class HeapBasePriorityQueueTest {

    @Test
    public void test() {
        HeapBasePriorityQueue queue = new HeapBasePriorityQueue();

        queue.add(12);
        queue.add(14);
        queue.add(6);
        queue.add(8);
        queue.add(7);
        queue.add(20);
        queue.add(4);
        queue.add(0);

        assertEquals(20, queue.poll());
        assertEquals(14, queue.poll());

        queue.add(30);

        assertEquals(30, queue.poll());

        queue.set(1, 15);

        assertEquals(15, queue.poll());
        assertEquals(12, queue.poll());
        // There is no 8 any more
        assertEquals(7, queue.poll());
    }

    /**
     * The class implementing the queue based on heap property. Allows to poll the element of the highest priority (the
     * biggest integer), add new elements and set the current elements to new value.
     * 
     * <p>
     * Created: 08.05.2012
     * </p>
     * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
     */
    private class HeapBasePriorityQueue {
        private List<Integer> heap = new ArrayList<Integer>();

        public int poll() {
            Integer next = heap.get(0);

            // Swaping the highest element with the tailed
            swap(heap, 0, heap.size() - 1);

            // Re heapify
            maxHeapify(heap, 0, heap.size() - 2);

            // Removing the last element (the one we should return)
            heap.remove(heap.size() - 1);

            return next;
        }

        public void add(Integer value) {
            // 1. Adding the element to the tail
            heap.add(Integer.MIN_VALUE);

            // 2. Setting it to new value
            set(heap.size() - 1, value);
        }

        public void set(int index, Integer newValue) {
            // 1. Set the element to new value
            heap.set(index, newValue);

            // 2. Re heapify (moving topward)
            int parentIdx = (int) Math.floor(index / 2);
            while (heap.get(parentIdx) < newValue) {
                swap(heap, parentIdx, index);
                index = parentIdx;
                parentIdx = (int) Math.floor(index / 2);
            }
        }

        /**
         * Performs the comparison of the element <code>array[i]</code> with its left and right children. If any of them
         * is bigger than the <code>i</code> element - swaps them and goes downward the tree.
         * @param array
         * @param i
         * @param lowBoundary the lower boundary of the heap. Procedures does not operate with elements which indexes
         *        are bigger than this parameter.
         */
        private void maxHeapify(List<Integer> array, int i, int lowBoundary) {
            int left, right;
            if (i == 0) {
                left = 1;
                right = 2;
            }
            else {
                left = i * 2;
                right = i * 2 + 1;
            }
            int max = i;
            if (array.size() > left && array.get(left) > array.get(max)) {
                max = left;
            }
            if (array.size() > right && array.get(left) > array.get(max)) {
                max = right;
            }
            if (max != i && max < lowBoundary) {
                swap(array, max, i);
                maxHeapify(array, max, lowBoundary);
            }
        }

        private void maxHeapify(List<Integer> array, int i) {
            maxHeapify(array, i, array.size());
        }

        /**
         * TBD
         * @param heap
         * @param p
         * @param i
         */
        private void swap(List<Integer> heap, int p, int i) {
            int t = heap.get(p);
            heap.set(p, heap.get(i));
            heap.set(i, t);
        }
    }
}

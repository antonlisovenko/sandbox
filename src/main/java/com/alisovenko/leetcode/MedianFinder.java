package com.alisovenko.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author alisovenko
 *         2/12/16.
 */
public class MedianFinder {
    private PriorityQueue<Integer> heap1 = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> heap2 = new PriorityQueue<>();
    Integer x;

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (x == null) {
            x = num;
        } else if (heap1.size() == 0) {
            if (x < num) {
                heap1.offer(x);
                heap2.offer(num);
            } else {
                heap1.offer(num);
                heap2.offer(x);
            }
        } else {
            // both heaps are initialized
            if (heap1.size() < heap2.size()) {
                if (heap2.peek() >= num) {
                    heap1.offer(num);
                } else {
                    heap1.offer(heap2.poll());
                    heap2.offer(num);
                }
            } else if (heap1.size() > heap2.size()) {
                if (heap1.peek() <= num) {
                    heap2.offer(num);
                } else {
                    heap2.offer(heap1.poll());
                    heap1.offer(num);
                }
            } else {
                // equal
                if (heap1.peek() <= num)  heap2.offer(num);
                else heap1.offer(num);
            }
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (heap1.size() == 0 && heap2.size() == 0 && x == null) throw new IllegalStateException();
        if (heap1.size() == 0) return (double)x;
        if (heap1.size() == heap2.size()) return (double) (heap1.peek() + heap2.peek()) / 2;
        if (heap1.size() > heap2.size()) return (double) heap1.peek();
        return (double) heap2.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
    }
}

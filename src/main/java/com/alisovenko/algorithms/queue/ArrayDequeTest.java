package com.alisovenko.algorithms.queue;

import org.junit.Test;

import java.util.Deque;

import java.util.ArrayDeque;

/**
 * Test for {@link ArrayDeque}
 *
 * <p>Created: 19.08.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class ArrayDequeTest {
    @Test
    public void test() {
        Deque<Integer> deque = new ArrayDeque<Integer>(7);
        
        
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addLast(2);
        deque.addLast(1);
        deque.addLast(0);
    }

}

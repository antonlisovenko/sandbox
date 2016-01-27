package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author alisovenko
 *         1/9/16.
 */
public class ZigZagIterator {
    private List<Iterator<Integer>> allIterators;
    private Integer nextVal;
    private int pos;

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        allIterators = new ArrayList<>();
        allIterators.add(v1.iterator());
        allIterators.add(v2.iterator());

        fetchNext();
    }

    public int next() {
        if (nextVal == null) throw new IllegalStateException();
        Integer r = nextVal;
        fetchNext();
        return r;
    }

    private void fetchNext() {
        int N = allIterators.size();
        for (int i = 0; i < N; i++) {
            Iterator<Integer> nextIterator = allIterators.get(pos);
            pos = ++pos % N;
            if (nextIterator.hasNext()) {
                nextVal = nextIterator.next();
                return;
            }
        }
        nextVal = null;
    }

    public boolean hasNext() {
        return nextVal != null;
    }
}

package com.alisovenko.algorithms.structure;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: alisovenko Date: 24.12.12
 */
public class Heap<T extends Comparable<T>> {
    private List<T> heap = new ArrayList<T>();
    private boolean max;
    private Map<T, Integer> dictionary = new IdentityHashMap<T, Integer>();;

    public Heap() {
    }

    public Heap(boolean max) {
        this.max = max;
    }

    public Heap(List<T> input) {
        this(input, false);
    }

    public Heap(List<T> input, boolean isMax) {
        max = isMax;
        dictionary = createDictionary(input);
        createHeap(input);
    }

    public void add(T newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException();
        }

        heap.add(newElement);
        dictionary.put(newElement, heap.size() - 1);

        // Heapify upword the latest element
        revertHeapify(heap.size() - 1);
    }

    public T extractMaxMin() {
        if (heap.size() == 0) {
            return null;
        }
        T result = heap.get(0);

        heap.set(0, heap.get(heap.size() - 1));
        
        dictionary.put(heap.get(0), 0);

        heapify(0);

        heap.remove(heap.size() - 1);
        //dictionary.remove(result);

        return result;
    }

    public void increaseKey(T element) {
        Integer idx = dictionary.get(element);
        if (idx == null) {
            throw new IllegalStateException("No element " + element + " found in heap!");
        }
        revertHeapify(idx);
    }

    public void decreaseKey(T element) {
        heapify(dictionary.get(element));
    }

    public void remove(T element) {
        // TODO
    }

    public Object[] toArray() {
        return heap.toArray();
    }

    public int size() {
        return heap.size();
    }

    private Map<T, Integer> createDictionary(List<T> input) {
        Map<T, Integer> dictionary = new IdentityHashMap<T, Integer>();
        for (int i = 0; i < input.size(); i++) {
            dictionary.put(input.get(i), i);
        }
        return dictionary;
    }

    private void revertHeapify(int i) {
        T current = safeGet(i);
        T parent = safeGet(i / 2);

        if (compare(current, parent)) {
            // swap first with current
            swap(heap, i, i / 2);
            revertHeapify(i / 2);
        }

    }

    private void createHeap(List<T> input) {
        // Defensive copy
        heap = new ArrayList<T>(input);
        for (int i = input.size() / 2; i >= 0; i--) {
            heapify(i);
        }
    }


    private void heapify(int i) {
        int f = i == 0 ? 1 : (i << 1);
        int s = i == 0 ? 2 : ((i << 1) + 1);
        T current = safeGet(i);
        T firstChild = safeGet(f);
        T secondChild = safeGet(s);

        int max = i;
        if (compare(firstChild, current)) {
            max = f;
        }
        if (compare(secondChild, safeGet(max))) {
            max = s;
        }
        /*System.out.println("Current [ " + i + " ]: " + current + ", first child [" + (i<<1) + "]: " + firstChild
                + ", second child [" + ((i << 1) + 1) + "] : " + secondChild + ". Max: " + max);*/
        if (max != i) {
            // swap second with current
            //System.out.println("Swapping [" + i + "]: " + current +  " with [" + max + "] " + safeGet(max));
            swap(heap, i, max);
            //System.out.println(Arrays.toString(heap.toArray()));
            heapify(max);
        }
    }

    private void swap(List<T> array, int a, int b) {
        T temp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, temp);
        dictionary.put(array.get(b), b);
        dictionary.put(array.get(a), a);
    }

    private T safeGet(int index) {
        if (heap.size() <= index) {
            return null;
        }
        return heap.get(index);
    }

    private boolean compare(T first, T second) {
        if (first == null || second == null) {
            return false;
        }
        if (max) {
            return first.compareTo(second) > 0 ? true : false;
        } else {
            return first.compareTo(second) < 0 ? true : false;
        }
    }
}

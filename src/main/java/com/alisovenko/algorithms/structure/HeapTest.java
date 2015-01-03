package com.alisovenko.algorithms.structure;

import com.google.common.collect.MinMaxPriorityQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * User: alisovenko Date: 24.12.12
 */
public class HeapTest {

    @Test
    public void test() {
        Random random = new Random();
        List<Integer> testData = new ArrayList<Integer>();

        for (int i = 0; i < 20; i++) {
            testData.add(random.nextInt(500));
        }

        MinMaxPriorityQueue<Integer> ethalon = MinMaxPriorityQueue.create(testData);
        PriorityQueue<Integer> ethalon2 = new PriorityQueue<Integer>(testData);

//        Heap<Integer> heap = new Heap<Integer>(testData, false);
        Heap<Integer> heap = new Heap<Integer>(false);

        for (Integer integer : testData) {
            heap.add(integer);
        }

        checkHeapProperty(heap);

        Integer[] res = new Integer[ethalon.size()];
        Integer i = 0, p = 0;
        while ((p = ethalon.pollFirst()) != null) {
            res[i++] = p;
        }

        System.out.println(Arrays.toString(heap.toArray()));
        Integer[] res2 = new Integer[heap.size()];
        i = 0;
        p = 0;
        while ((p = heap.extractMaxMin()) != null) {
            res2[i++] = p;
        }
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(res2));

        Assert.assertArrayEquals(res, res2);
    }

    private void checkHeapProperty(Heap<Integer> heap) {
        Object[] arr = heap.toArray();

        int i = 0;
        for (Object o : arr) {
            Integer th = (Integer) o;
            Integer th1;
            Integer th2;
            try {
                th1 = (Integer) arr[i == 0 ? 1 : i << 1];
                th2 = (Integer) arr[i == 0 ? 2 : (i << 1) + 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }

            if (th1 != null) {
                junit.framework.Assert.assertTrue(th.compareTo(th1) <= 0);
            }
            if (th2 != null) {
                junit.framework.Assert.assertTrue(th.compareTo(th2) <= 0);
            }
            i++;
        }

    }
}

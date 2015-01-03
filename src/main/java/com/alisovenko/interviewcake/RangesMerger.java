package com.alisovenko.interviewcake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 20.10.14
 */
public class RangesMerger {
    public static List<int[]> merge(int[][] ranges) {
        List<int[]> result = new ArrayList<>();
        Item[] items = new Item[ranges.length * 2];

        int i = 0;
        for (int[] pair: ranges) {
            items[i++] = new Item(pair[0], true);
            items[i++] = new Item(pair[1], false);
        }

        Arrays.sort(items, (c, n) -> Integer.compare(c.i, n.i) == 0 ? (!c.isOpening ? 1 : -1) : Integer.compare(c.i, n.i));

        int lo = -1;
        int cnt = 0;

        for (Item item : items) {
            assert cnt >= 0 : "WTF with input data?";

            if (item.isOpening) {
                cnt++;
            }
            else {
                cnt--;
            }

            if (lo < 0) {
                lo = item.i;
            }

            if (cnt == 0) {
                // we just found new disconnected range
                result.add(new int[]{lo,  item.i});
                lo = -1;
            }

        }
        assert cnt == 0;
        return result;

    }

    private static class Item{
        int i;
        boolean isOpening = false;

        public Item(int i, boolean isO) {
            this.i = i;
            this.isOpening = isO;
        }
    }

    public static void main(String[] args) {
        merge (new int[][]{{0, 1}, {1, 5}, {4, 8}, {10, 12}, {9, 10}, {5, 6}}).forEach(s -> System.out.println(Arrays.toString(s)));
        merge (new int[][]{{0, 1}, {1, 10}, {2, 6}, {3, 5}, {7,9}}).forEach(s -> System.out.println(Arrays.toString(s)));
    }

}
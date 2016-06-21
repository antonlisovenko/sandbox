package com.alisovenko.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author alisovenko
 *         2/14/16.
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        int n = nums.length;

        int[] sorted = new int[n];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        // Initializing items with frequency count
        List<Item> items = new ArrayList<>();

        items.add(new Item(1, sorted[0], 1));

        for (int i = 1; i < n; i++) {
            Item last = items.get(items.size() - 1);
            if (last.key == sorted[i]) last.count = ++last.num;
            else items.add(new Item(1, sorted[i], 1));
        }

        System.out.println("Items: " + items);

        // Updating count of binary nodes
        updateCounts(items, 0, items.size() - 1);

        System.out.println("Items with counts: " + items);

        // Calculation of result
        List<Integer> result = new ArrayList<>(n);

        for (int num : nums) {
            result.add(decreaseRank(items, 0, items.size() - 1, num));
            System.out.println("Items: " + items);
        }

        return result;
    }

    private int decreaseRank(List<Item> tree, int from, int to, int k) {
        if (to < from) return 0;

        int med = from + (to - from) / 2;
        int lIdx = from + (med - from - 1) / 2;
        int cnt = 0;

        Item current = tree.get(med);
        int leftCount = med > from ? tree.get(lIdx).count : 0;

        if (current.key == k) {
            cnt = leftCount;
            current.num--;
        } else if (current.key > k) {
            cnt = decreaseRank(tree, from, med - 1, k);
        } else /*tree[med] < k*/ {
            cnt = leftCount + current.num + decreaseRank(tree, med + 1, to, k);
        }

        current.count--;

        return cnt;
    }

    private int updateCounts(List<Item> tree, int from, int to) {
        if (to < from) return 0;
        int med = from + (to - from) / 2;

        tree.get(med).count += updateCounts(tree, from, med - 1) + updateCounts(tree, med + 1, to);

        return tree.get(med).count;
    }

    private static class Item {
        int count;
        int num;
        int key;

        public Item(int count, int key, int num) {
            this.count = count;
            this.key = key;
            this.num = num;
        }

        @Override
        public String toString() {
            return key + "(" + count + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{5, 2, 6, 1}));
    }
}

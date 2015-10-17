package com.alisovenko.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author alisovenko 21.06.15
 */
public class LRUCache {
    private HashMap<Integer, Integer> map;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return this.size() > capacity;
            }
        };
    }

    public int get(int key) {
        return map.containsKey(key) ? map.get(key) : -1;
    }

    public void set(int key, int value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(1);

        lruCache.set(2, 1);
        System.out.println(lruCache.get(2));
    }
}
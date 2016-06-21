package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/6/16.
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = "";
            char first = str.charAt(0);
            for (char c : str.toCharArray())
                key += (c - first + 26) % 26 + ",";
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(str);
        }
        for (String key : map.keySet())
            Collections.sort(map.get(key));
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(new GroupShiftedStrings().groupStrings(new String[]{"az", "ba"}));
    }
}

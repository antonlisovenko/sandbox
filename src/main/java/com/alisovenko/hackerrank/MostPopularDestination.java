package com.alisovenko.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author alisovenko
 *         11/22/15.
 */
public class MostPopularDestination {
    static void OutputMostPopularDestination(int count, Scanner in) {
        if (count == 0) {
            return;
        }
        Map<String, Integer> multimap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String city = in.next();
            if (!multimap.containsKey(city)) {
                multimap.put(city, 1);
            }
            else {
                multimap.put(city, multimap.get(city) + 1);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : multimap.entrySet()) {
            if (maxEntry == null || maxEntry.getValue() < entry.getValue()) {
                maxEntry = entry;
            }
        }

        System.out.println(maxEntry.getKey());
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputMostPopularDestination(_count, in);

    }
}

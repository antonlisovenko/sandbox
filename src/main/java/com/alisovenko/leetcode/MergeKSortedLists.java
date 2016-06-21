package com.alisovenko.leetcode;

import java.util.*;

/**
 * @author alisovenko
 *         2/1/16.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode current = null;
        ListNode root = null;

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((Comparator<ListNode>) (o1, o2) -> Integer.compare(o1.val, o2.val));

        // First initialization of priority queue
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) priorityQueue.add(lists[i]);
        }

        while (priorityQueue.size() > 0) {
            ListNode next = priorityQueue.poll();

            if (next.next != null) {
                priorityQueue.add(next.next);
            }
            if (current == null) {
                root = next;
            } else {
                current.next = next;
            }
            current = next;
        }

        return root;
    }
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

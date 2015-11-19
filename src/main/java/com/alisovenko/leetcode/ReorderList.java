package com.alisovenko.leetcode;

import java.util.Stack;

/**
 * @author alisovenko
 *         11/13/15.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        Stack<ListNode> stack = new Stack<ListNode>();

        // Filling the stack
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // Interleaving nodes
        ListNode currentLeft = head;
        ListNode currentRight = stack.pop();
        ListNode next = stack.size() > 1 ? currentLeft.next : null;

        while (currentLeft != currentRight // if they are the same - we have the odd number of nodes
                && next != currentRight) /* if next is the same - we have the even number of nodes*/ {
            currentLeft.next = currentRight;
            currentRight.next = next;
            currentRight = stack.pop();
            currentLeft = next;
            next = next.next;
        }
        currentRight.next = null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode other = new ListNode(2);
        ListNode other2 = new ListNode(3);
        head.next = other;
        other.next = other2;

        printList(head);
        new ReorderList().reorderList(head);
        printList(head);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head);
            head = head.next;
        }
        System.out.println();
    }

    public static class ListNode {
           int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return "" + val;
        }
    }

}

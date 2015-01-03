package com.alisovenko.cracking.the.interview._3_stacks_queues;

import java.util.*;

/**
 * @author alisovenko 19.08.14
 */
public class _3_3 {
    public static class SetOfStacks {
        private final int threshold;
        private Stack<Integer> current = new Stack<>();
        private List<Stack<Integer>> listOfStacks = new ArrayList<>(Arrays.asList(current));
        private int currStackIdx = 0;
        private List<Integer> sizes = new ArrayList<>(Arrays.asList(0));

        public SetOfStacks(int threshold) {
            this.threshold = threshold;
        }

        public void push(int i) {
            if (sizes.get(currStackIdx) == threshold) {
                nextStack();
            }
            current.push(i);
            sizes.set(currStackIdx, sizes.get(currStackIdx) + 1);
        }

        public int pop() {
            if (sizes.get(currStackIdx) == 0) {
                previousStack();
            }
            if (current == null) {
                throw new RuntimeException("No elements");
            }
            sizes.set(currStackIdx, sizes.get(currStackIdx) - 1);
            return current.pop();
        }

        public int popAt(int i) {
            if (sizes.get(i) == 0) {
                throw new RuntimeException();
            }
            sizes.set(i, sizes.get(i) - 1);
            return listOfStacks.get(i).pop();
        }

        private void nextStack() {
            currStackIdx++;
            if (listOfStacks.size() <= currStackIdx) {
                listOfStacks.add(new Stack());
                sizes.add(0);
            }
            sizes.set(currStackIdx, 0);
            current = listOfStacks.get(currStackIdx);
        }

        private void previousStack() {
            currStackIdx--;
            if (currStackIdx == -1) {
                throw new RuntimeException();
            }
            current = listOfStacks.get(currStackIdx);
        }

        @Override
        public String toString() {
            return "SetOfStacks{" +
                    "sizes=" + sizes +
                    ", currStackIdx=" + currStackIdx +
                    ", current=" + current +
                    ", listOfStacks=" + listOfStacks +
                    '}';
        }
    }

    public static void main(String[] args) {
        SetOfStacks setOfStacks = new SetOfStacks(4);
        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.push(3);
        setOfStacks.push(4);
        setOfStacks.push(5);
        System.out.println(setOfStacks.pop());
        System.out.println(setOfStacks.pop());
        setOfStacks.push(6);
        setOfStacks.push(7);
        System.out.println(setOfStacks.popAt(0));
        System.out.println(setOfStacks);

    }
}

package com.alisovenko.cracking.the.interview._3_stacks_queues;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alisovenko 07.02.15
 */
public class _3_4 {
    private static class Tower {
        List<Integer> discs = new ArrayList<>();

        public void moveLastDisk(Tower destination) {
            // last disc to destination
            Integer disc = this.discs.remove(discs.size() - 1);
            destination.discs.add(disc);
        }
        public void move(Tower destination, Tower buffer) {
            move(discs.size(), destination, buffer);
        }
        public void move(int number, Tower destination, Tower buffer) {
            if (number < 1) {
                return;
            }
            move(number - 1, buffer, destination);
            moveLastDisk(destination);
            buffer.move(number - 1, destination, this);
        }

        @Override
        public String toString() {
            return discs.toString();
        }
    }

    public static void main(String[] args) {
        Tower[] towers = new Tower[3];
        towers[0] = new Tower();
        towers[1] = new Tower();
        towers[2] = new Tower();

        towers[0].discs = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        System.out.println("BEFORE:");
        System.out.printf("Tower 1: %s\n", towers[0]);
        System.out.printf("Tower 2: %s\n", towers[1]);
        System.out.printf("Tower 3: %s\n", towers[2]);

        towers[0].move(towers[1], towers[2]);

        System.out.println("AFTER:");

        System.out.printf("Tower 1: %s\n", towers[0]);
        System.out.printf("Tower 2: %s\n", towers[1]);
        System.out.printf("Tower 3: %s\n", towers[2]);
    }
}

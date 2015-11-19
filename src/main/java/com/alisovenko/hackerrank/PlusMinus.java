package com.alisovenko.hackerrank;


import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alisovenko
 *         11/17/15.
 */
public class PlusMinus {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        String a;
        a = in.next();
        a = in.next();
        String[] tokens = a.split(" ");
        AtomicInteger pos = new AtomicInteger(0), neg = new AtomicInteger(0), zer = new AtomicInteger(0);
        Arrays.stream(tokens).map(Integer::valueOf).forEach((c) -> {
            if (c < 0) neg.incrementAndGet();
            else if (c > 0) pos.incrementAndGet();
            else zer.incrementAndGet();
        });
        System.out.println((double)pos.get() / tokens.length);
        System.out.println((double)neg.get() / tokens.length);
        System.out.println((double)zer.get() / tokens.length);

    }
}

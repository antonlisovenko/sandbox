package com.alisovenko.geeks4geeks;

/**
 * @author alisovenko 11.03.15
 */
public class UglyNumbers {
    public static int find150number() {
        boolean[] cache = new boolean[10000];
        cache[0] = cache[1] = false;
        int cnt = 0;
        cache[2] = cache[3] = cache[4] = cache[5] = true;

        for (int i = 6; i < cache.length; i++) {
            if (cache[i / 2] || cache[i / 3] || cache[i / 5]) {
                cache[i] = true;
                cnt++;
                if (cnt == 150) {
                    return i;
                }
                System.out.printf("Found %d (%d)\n", i, cnt);
            } else {
                cache[i] = false;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(find150number());
    }
}

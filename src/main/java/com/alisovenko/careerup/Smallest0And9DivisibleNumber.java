package com.alisovenko.careerup;

/**
 * @author alisovenko 22.09.14
 */
public class Smallest0And9DivisibleNumber {
    public static int find(int divisible) {
        int bin = 1;
        while (true) {
            int res = translate(bin);
            if (res % divisible == 0) {
                return res;
            }
            bin += 1;
        }
    }

    private static int translate(int bin) {
        int result = 0;
        for (int i = Integer.toBinaryString(bin).length(); i > 0; i--) {
            result *= 10;
            int mask = 1 <<  (i - 1);
            result += (bin & mask) == mask ? 9 : 0;
        }
        return result;
    }

    public static void main(String[] args) {
        assert find(10) == 90;
        assert find(99) == 99;
        assert find(33) == 99;
        assert find(3) == 9;
        assert find(333) == 999;
        assert find(300) == 900;
        assert find(303) == 909;
        assert find(3033) == 9099;
        assert find(3303) == 9909;
    }
}

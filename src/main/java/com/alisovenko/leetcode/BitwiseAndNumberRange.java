package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/8/16.
 */
public class BitwiseAndNumberRange {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n)
            return m;
        // Find the first '1' from the left
        int mPos = findLeftmost_1(m);

        int nPos = findLeftmost_1(n);

        if (mPos != nPos) return 0;

        // Ok, two numbers have the same length - taking the equal prefix
        int res = 0;
        for (int i = mPos; i >= 0; i--) {
            if ((n & (1 << i)) == (m & (1 << i)))
                res |= (n & (1 << i));
            else
                break;
        }
        return res;
    }

    private int findLeftmost_1(int m) {
        int mPos = 0;
        for (int i = 31; i >= 0; i--) {
            if ((m & (1 << i)) != 0) {
                mPos = i;
                break;
            }
        }
        return mPos;
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseAndNumberRange().rangeBitwiseAnd(5, 7));
        System.out.println(new BitwiseAndNumberRange().rangeBitwiseAnd(10, 11));
        System.out.println(new BitwiseAndNumberRange().rangeBitwiseAnd(600, 453453));
    }
}

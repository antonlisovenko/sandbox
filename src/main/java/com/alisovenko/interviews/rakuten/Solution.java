package com.alisovenko.interviews.rakuten;

import java.math.BigInteger;

/**
 * @author alisovenko 25.02.15
 */
class Solution {
    public int solution(int K, int L, int M, int N, int P, int Q, int R, int S) {
        BigInteger lHeight = BigInteger.valueOf(N - L);
        BigInteger lWidth = BigInteger.valueOf(M - K);

        BigInteger lArea = lHeight.multiply(lWidth);

        BigInteger rHeight = BigInteger.valueOf(S - Q);
        BigInteger rWidth = BigInteger.valueOf(R - P);

        BigInteger rArea = rHeight.multiply(rWidth);

        BigInteger intersactionArea = BigInteger.ZERO;

        if (K < R && M > P && L < S && N > Q) {
            BigInteger xOverlap = BigInteger.valueOf(Math.max(0, Math.min(M, R) - Math.max(K, P)));
            BigInteger yOverlap = BigInteger.valueOf(Math.max(0, Math.min(N, S) - Math.max(L, Q)));
            intersactionArea = xOverlap.multiply(yOverlap);
        }

        BigInteger total = lArea.add(rArea).subtract(intersactionArea);

        if (total.compareTo(BigInteger.valueOf(2147483647l)) > 0) {
            return -1;
        }
        return total.intValue();
    }
}


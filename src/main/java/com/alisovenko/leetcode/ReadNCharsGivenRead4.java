package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/27/16.
 */
public class ReadNCharsGivenRead4 {
    int lastPos = 0;
    int lastLength = 0;
    char[] b = new char[4];
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int N = Math.min(buf.length, n);
        int c = 0;

        if (lastPos > 0) {
            // size to read from saved buffer
            int size = Math.min(lastLength - lastPos, N);
            copy(b, buf, size, lastPos, 0);
            // Any data left in local buffer?
            if (lastPos + size < lastLength) {
                lastPos = lastPos + size;
            } else {
                // All data from local buffer read
                lastPos = 0;
                lastLength = 0;
            }
            c += size;
        }

        while (c < N) {
            b = new char[4];
            int charsRead = read4(b);
            // We should copy at most number of characters left to reach N
            int length = Math.min(charsRead, N - c);
            copy(b, buf, length, 0, c);
            c += charsRead;

            // If we copied only part of read characters - this is the last loop cycle, we need to save buffer pointers for next invocations
            if (length < charsRead) {
                lastPos = length;
                lastLength = charsRead;
            }
            // File is fully read
            if (charsRead < 4) break;
        }

        return Math.min(c, N);
    }

    // mock
    int read4(char[] buf){return 0;}

    private void copy(char[] src, char[] dest, int length, int srcPos, int destPos) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }
}

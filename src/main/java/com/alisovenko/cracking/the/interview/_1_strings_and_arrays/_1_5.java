package com.alisovenko.cracking.the.interview._1_strings_and_arrays;

/**
 * @author alisovenko 10.08.14
 */
public class _1_5 {
    public static void main(String[] args) {
        String res = compress("ddddgggcr");
        assert res.equals("d4g3c1r1") : "Result: " + res;
    }
    public static String compress(String in) {
        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        char cur = in.charAt(0);
        for(char ch: in.toCharArray()) {
            if (cur != ch) {
                sb.append(cur).append(cnt);
                cnt = 0;
            }
            cnt++;
            cur = ch;
        }

        // tail
        sb.append(cur).append(cnt);

        return sb.length() < in.length() ? sb.toString() : in;
    }
}

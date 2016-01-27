package com.alisovenko.leetcode;

/**
 * @author alisovenko
 *         1/4/16.
 */
public class CoundAndSay {
    public String countAndSay(int n) {
        if (n <= 0) return null;

        String s1 = "1";

        StringBuilder sb;

        for (int i = 1; i < n; i++) {
            sb = new StringBuilder();

            char c = s1.charAt(0);
            int cnt = 1;
            for (int p = 1; p < s1.length(); p++) {
                char s = s1.charAt(p);
                if (s != c) {
                    sb.append(cnt).append(c);
                    c = s;
                    cnt = 0;
                }
                cnt++;
            }
            // tail
            sb.append(cnt).append(c);
            s1 = sb.toString();
        }

        return s1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new CoundAndSay().countAndSay(i));
        }
    }
}

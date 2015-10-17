package com.alisovenko.penguin.google.onsite;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Матчинг паттернов. Дана строка и паттерн, который может содержать *. Надо ответить, подходит ли строка под паттерн.
 * Есть решение за O(n*m) времени и O(n) памяти, хотя можно начать с тупого рекурсивного подхода в качестве разминки.
 *
 * Например, для fooxxxbar и foo*bar ответ «да».
 *
 * ----------------
 *
 * Сделал по-тупому: разбиваю строки по звездочкам. Проверяю первый и последний токен - они должны быть железно в начале и в конце.
 * Остальные просто нахожу в подстроках друг за другом.
 *
 * @author alisovenko 03.01.15
 */
public class PatternMatching {
    public static boolean matches(String str, String pattern) {
        // Splitting can be done manually
        String[] splitted = pattern.split("\\*");

        if (splitted.length == 1) {
            return str.equals(pattern);
        }

        // Otherwise first let's check the head and tail
        String head = splitted[0];
        String tail = splitted[splitted.length-1];
        if (!str.startsWith(head)) {
            System.out.println("doesn't start with");
            return false;
        }
        if (!str.endsWith(tail)) {
            System.out.println("doesn't end with");
            return false;
        }

        // let's check the other words are in the incoming string in the same order
        int lastMatchedPos= head.length() - 1;
        int finish = str.length() - tail.length();

        for (int i = 1; i < splitted.length - 1; i++) {
            String s = splitted[i];
            String substring = str.substring(lastMatchedPos);
            int next = substring.indexOf(s);

            if (next < 0 || (next + s.length()) >= finish) {
                System.out.printf("Word: %s, substring: %s, index: %d\n", s, substring, next);
                return false;
            }

            lastMatchedPos = next + s.length();
        }

        return true;

    }

    public static Collection<String> getMatches(String t, String p) {
        Collection<String> result = new ArrayList<>();
        for (int i = 0; i < t.length(); i++) {

            int j = 0;
            int h = i;
            int n = p.length();

            while (true){
                int L = longestCommonExtension(p, j, t, h);

                if (j + 1 + L == n + 1) {
                    result.add(t.substring(i, i + n));
                    break;
                }

                if (((j + L) < p.length() && p.charAt(j + L) == '*')
                        || ((h + L) < t.length() && t.charAt(h + L) == '*')) {
                    j = j + L + 1;
                    h = h + L + 1;
                } else
                    break;
            }
        }
        return result;
    }

    public static int longestCommonExtension(String t1, int i1, String t2, int i2) {
        int res = 0;
        for (int i = i1; i < t1.length() && i2 < t2.length(); i++, i2++) {
            if (t1.charAt(i) == t2.charAt(i2))
                res++;
            else
                return res;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(getMatches("abentbananaend bend", "ben*"));
        System.out.println(matches("hello", "hello"));
        System.out.println(matches("helloWorld", "hello*World"));
        System.out.println(matches("helloXXXWorld", "hello*World"));
        System.out.println(matches("hello d this x beautiful p World", "hello*this*beautiful*World"));
        System.out.println(matches("hello d beautiful x this p World", "hello*this*beautiful*World"));
    }
}

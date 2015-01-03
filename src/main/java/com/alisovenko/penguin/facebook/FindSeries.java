package com.alisovenko.penguin.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1 1
 * 2 1
 * 1 2 1 1
 * 1 1 1 2 2 1
 * 3 1 2 2 1 1
 * 1 3 1 1 2 2 2 1
 * <p>
 * Предлагается угадать следующее значение. Предупреждается, что правило совсем простое и наивное. Если не угадал, то
 * типа не страшно. Ответ в конце. Необходимо написать программу, которая получает номер строки и выводит эту строку.
 *
 * Ответ на 1. Каждая следующая строка получается как бы произношением предыдущей: "1 1" = "Две единицы" ->
 * "2 1", "2 1" = "Одна двойка, одна единица" -> "1 2 1 1".
 *
 * @author alisovenko 07.10.14
 */
public class FindSeries {
    private static List<Integer> findSeries(int level, List<Integer> initial) {
        int counter = 0;
        List<Integer> interim = new ArrayList<>(initial);
        List<Integer> interim2 = new ArrayList<>();

        while (counter <= level) {
            if (counter % 2 == 0) {
                populate(interim, interim2);
            } else {
                populate(interim2, interim);
            }

            counter++;
        }
        return level % 2 == 0 ? interim2 : interim;
    }

    private static void populate(List<Integer> source, List<Integer> target) {
        assert source.size() > 0;
        target.clear();

        int cur = source.get(0);
        int cnt = 0;
        for (final Integer i : source) {
            if (i != cur) {
                // digit changed, we need to process previous ones
                target.add(cnt);
                target.add(cur);

                // reset values
                cur = i;
                cnt = 0;
            }
            cnt++;
        }

        // tail
        target.add(cnt);
        target.add(cur);

        System.out.println(">> " + target.toString());
    }

    public static void main(String[] args) {
        System.out.println(findSeries(4, Arrays.asList(1, 1)));
        System.out.println(findSeries(2, Arrays.asList(4, 4, 4, 5, 9)));
        System.out.println(findSeries(3, Arrays.asList(1, 2, 3)));
    }
}

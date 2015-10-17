package com.alisovenko.misc;

/**
 * @author alisovenko 05.01.15
 */
public class ConvertToRoman {
    private static StringBuilder builder;

    public static String transformToRomanian(int x) {
        builder = new StringBuilder();
        int thousands = x / 1000;
        int hundreds = (x % 1000) / 100;
        int tens = ((x % 1000) % 100) / 10;
        int ones = ((x % 1000) % 100) % 10;

        System.out.printf("th: %d, h: %d, t: %d, o: %d\n", thousands, hundreds, tens, ones);
        append('M', thousands);
        append('C', hundreds);
        append('X', tens);
        append('I', ones);

        return builder.toString();
    }
    private static void append (char c, int num) {
        char p = ' ', n = ' ';
        switch (c) {
            case 'M': break;
            case 'C': p = 'D'; n = 'M'; break;
            case 'X': p = 'L'; n = 'C'; break;
            case 'I': p = 'V'; n = 'X'; break;
        }

        if (num == 9) {
            builder.append(c);
            builder.append(n);
        }
        else if (num >= 5 ) {
            builder.append(p);
            if (num == 6) builder.append(c);
            if (num >= 7) builder.append(c);
            if (num >= 8) builder.append(c);
        }
        else {
            if (num == 1) builder.append(c);
            if (num <= 2) builder.append(c);
            if (num <= 3) builder.append(c);
            if (num <= 4) builder.append(p);
        }
    }

    public static void main(String[] args) {
        System.out.println(transformToRomanian(9));
        System.out.println(transformToRomanian(99));
        System.out.println(transformToRomanian(999));
        System.out.println(transformToRomanian(348));
        System.out.println(transformToRomanian(295));
        System.out.println(transformToRomanian(87));
        System.out.println(transformToRomanian(4132));
    }
}

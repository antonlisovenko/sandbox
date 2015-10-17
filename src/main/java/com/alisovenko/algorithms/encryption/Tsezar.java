package com.alisovenko.algorithms.encryption;

/**
 * @author alisovenko 25.04.15
 */
public class Tsezar {
  private static int R = 'я' - 'А' + 2;
  private static int SHIFT = 3;
  private static char[] cipher = new char[R];
  private static char[] decodeCipher = new char[R];

  public static void main(String[] args) {
    String key = "шифровка";
    String phrase = "Лисовенко Антон Витальевич Системы передачи информации Кудрявцев  Григорий Михайлович";

    prepareCipher(key);

    encode(phrase);
  }

  private static void encode(String phrase) {
    String s = "";
    for (final char c : phrase.toCharArray()) {
      if (c == ' ')
        s += cipher[R - 1];
      else
        s += cipher[c - 'А'];
    }
    System.out.printf("Output : %s\n", s);

    String v = "";
    for (final char c : s.toCharArray()) {
      final char c1 = decodeCipher[c - 'А'];
      if (c1 == 'я' + 1) {
        v += ' ';
      }
      else {
        v += c1;
      }
    }
    System.out.printf("Decoded: %s\n", v);
  }

  private static void prepareCipher(String key) {
    boolean[] dict = new boolean[R];
    String uniqueKey = "";
    for (final char c : key.toCharArray()) {
      if (!dict[c - 'А']) {
        uniqueKey += c;
      }
      dict[c - 'А'] = true;
    }
    final int L = uniqueKey.length();

    // first encode the keywoard with unique characters with defined offset
    for (int i = 0; i < L; i++) {
      cipher[i + SHIFT] = uniqueKey.charAt(i);
      decodeCipher[uniqueKey.charAt(i) - 'А'] = (char)(i + SHIFT + 'А');

    }

    // then encode the rest of alphabet
    int pos = SHIFT + L;
    for (int i = 0; i < R; i++) {
      if (!dict[i]) {
        cipher[pos] = (char) ('А' + i);
        decodeCipher[i] = (char)(pos + 'А');
        pos = (++pos)% R;
      }
    }
  }
}

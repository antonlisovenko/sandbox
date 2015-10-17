package com.alisovenko.algorithms.encryption;

/**
 * @author alisovenko 25.04.15
 */
public class Tritemius {
  private static int R = 'я' - 'А' + 2; // with whitespace
  private static int WHITESPACE_IDX = 'я' + 1;

  public static void main(String[] args) {
    String key = "шифровка";
    String phrase = "Лисовенко Антон Витальевич Системы передачи информации Кудрявцев  Григорий Михайлович";

    encode(phrase, key);
  }

  private static void encode(String phrase, String key) {
    System.out.printf("In     : %s\n", phrase);

    String s = "";
    int i = 0;
    for (final char c : phrase.toCharArray()) {
      int pos = (c == ' ' ? WHITESPACE_IDX :  c) - 'А';
      int coded = key.charAt(i++ % (key.length() - 1)) - 'А';

      s += (char)(((pos + coded) % R) + 'А');
    }
    System.out.printf("Encoded: %s\n", s);

    i = 0;
    String v = "";
    for (final char c : s.toCharArray()) {
      int pos = c - 'А';
      int coded = key.charAt(i++ % (key.length() - 1)) - 'А';

      char t;
      if (coded <= pos)
        t = (char) (((pos - coded) % R) + 'А');
      else
        t = (char) (((pos - coded + R) % R) + 'А');

      v += (t == WHITESPACE_IDX ? ' ' : t);
    }

    System.out.printf("Decoded: %s\n", v);

  }

}

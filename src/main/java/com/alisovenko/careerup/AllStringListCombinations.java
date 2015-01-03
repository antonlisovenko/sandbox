package com.alisovenko.careerup;

/**
 * http://www.careercup.com/question?id=5756808635351040
 *
 * Print combinations of strings from List of List of String

 Example input: [[quick, slow], [brown, red], [fox, dog]]

 Output:
 quick brown fox
 quick brown dog
 quick red fox
 quick red dog
 slow brown fox
 slow brown dog
 slow red fox
 slow red dog

 * @author alisovenko 26.10.14
 */
public class AllStringListCombinations {
    public static void print(String[][] strings) {
        permutate (strings, new int[strings.length], 0);
    }

    public static void permutate (String[][] strings, int[] indexes, int level) {
        if (level >= strings.length) {
            // we came to end, let's print the string
            for (int i = 0; i < strings.length; i++) {
                System.out.print(strings[i][indexes[i]]);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < strings[level].length; i++) {
            indexes[level] = i;
            permutate(strings, indexes, level + 1);
        }
    }

    public static void main(String[] args) {
        String[][] strings = {{"quick", "slow"}, {"brown", "red"}, {"fox", "dog"}};
        print(strings);
    }
}

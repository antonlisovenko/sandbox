package com.alisovenko.interviews.amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// -------------------
// |  1  |  2  |  3  |
// |     | ABC | DEF |
// -------------------
// |  4  |  5  |  6  |
// | GHI | JKL | MNO |
// -------------------
// |  7  |  8  |  9  |
// | PQRS| TUV | WXYZ|
// -------------------
//
// CALL JOSH
// 2255 5674
//
// 22 => ['AA', 'AB', .. 'CA', 'CB', 'CC']
// 21 -> ['a', 'b', 'c']
/**
 * @author alisovenko 29.04.15
 */
public class PhonePermutations {
    static char[][] dict;
    static {
        dict = new char[10][];
        dict[0] = new char[]{};
        dict[1] = new char[]{};
        dict[2] = new char[]{'a', 'b', 'c'};
        dict[3] = new char[]{'d', 'e', 'f'};
        dict[4] = new char[]{'g', 'h', 'i'};
        dict[5] = new char[]{'j', 'k', 'l'};
        dict[6] = new char[]{'m', 'n', 'o'};
        dict[7] = new char[]{'p', 'q', 'r', 's'};
        dict[8] = new char[]{'t', 'u', 'v'};
        dict[9] = new char[]{'w', 'x', 'y', 'z'};
    }
    // 'a', 'a', 'd', 'g' (current)
    // 'b', 'a', 'd', 'g' (current)
    // 'c', 'a', 'd', 'g' (current)
    // 'a', 'b', 'd', 'g' (current)
    // 'b', 'b', 'd', 'g' (
    // 'c', 'b', 'd', 'g' (current)
    // 'a', 'c', 'd', 'g' (current)
    //....
    private String str(List<Character> c) {
        StringBuilder sb = new StringBuilder();
        for (char t: c) {
            sb.append(t);
        }
        return sb.toString();
    }

    public void collect(int a, int max, int pos, List<String> result, LinkedList<Character> current) {
        if(pos == max) {
            // base case - found the whole permutation of character
            result.add(str(current));
            return;
        }
        int i = 0;
        int d = 0;
        int t = a;
        while (i++ <= pos) {
            d = t % 10;
            t = t / 10;
        }
        for (char c : dict[d]) {
            current.addFirst(c);
            collect(a, max, pos + 1, result, current);
            current.removeFirst();
        }
    }

    public List<String> permutate(int a) {
        List<String> result = new ArrayList<>();
        int t = a;
        int numberOfDigits = 0;
        while (t > 0) {
            t /= 10;
            numberOfDigits++;
        }
        collect(a, numberOfDigits, 0, result, new LinkedList<>());
        return result;
    }

    public static void main(String [] args) {
        System.out.println(new PhonePermutations().permutate(22));
    }
}
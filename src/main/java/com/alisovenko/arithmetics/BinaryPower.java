package com.alisovenko.arithmetics;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Binary power calculation. Performs the calculation of power not in O(n) operations but in O(lg(n)) as all powers are
 * recursivelly devided on 2. See http://e-maxx.ru/algo/binary_pow.
 * 
 * <p>
 * Created: 24.03.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BinaryPower {
    @Test
    public void test() {
        int i = 2;
        int power = 25;
        int expected = (int) Math.pow(i, power);
        int result = calculate(i, power);

        System.out.println("Math.pow: " + expected);

        System.out.println("Test: " + result);

        Assert.assertEquals(expected, result);
    }

    public int calculate(int num, int pow) {
        int digit;
        if (pow == 1) {
            return num;
        }
        if (pow % 2 == 1) {
            digit =  calculate(num, pow - 1);
            System.out.println((String.valueOf(num)) + " * " + digit + " (pow = " + pow + ")");
            return num * digit;
        }
        digit = calculate(num, pow / 2);
        System.out.println((String.valueOf(digit)) + " * " + digit + " (pow = " + pow + ")");
        return digit * digit;
    }

}

package com.alisovenko.arithmetics.binary;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * TBD: add comments for BitsGames.java.
 * 
 * <p>Created: 17.08.2012</p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class BitsGames {
    @Test
    public void test() {
        System.out.println((int)('3' - '0'));
        int b = Integer.MAX_VALUE - 1000;
        
        System.out.println("1) " + b + ": " + print(b));
        
        b = b << 3 ;
        
        System.out.println(b + ": b << 3: " + print(b) + " (we lose the sign!)");
        
        b = 0x80ff00f0;
        
        System.out.println("2) " + b + ": " + print(b));
        
        b = b << 3 ;
        
        System.out.println(b + ": b << 3: " + print(b) + " (we lose the sign!)");
        
        b = -500;

        System.out.println("3) " + b + ": " + print(b));
        
        b = b >> 6 ;
        
        System.out.println(b + ": b >> 6: " + print(b) + " (we don't lose the negative sign!)");
        
        b = -500;
        
        System.out.println("4) " + b + ": " + print(b));
        
        b = b >>> 6 ;
        
        System.out.println(b + ": b >>> 6: " + print(b) + " (we lose the negative sign as it is shifted!)");
    }

    private static String print(int b) {
        String s = Integer.toBinaryString(b);
        if (b> 0) {
            return Strings.padStart(s, 32, '0');
        }
        return s;
    }

}

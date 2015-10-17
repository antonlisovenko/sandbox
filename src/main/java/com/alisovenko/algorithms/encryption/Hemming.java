package com.alisovenko.algorithms.encryption;

import com.alisovenko.algorithms.externalsort.IntegerEqualToComparator;
import com.google.common.base.Charsets;

import java.util.Arrays;
import java.util.Random;

/**
 * @author alisovenko 05.05.15
 */
public class Hemming {
    public byte[] encode(String s) {
        byte[] info = s.getBytes();
        byte[] control = new byte[info.length / 2]; // 5 new control bits (1 byte) per each 16 informational bits

        int c = 0;
        for (int i = 0; i < info.length; i += 2) {
            short sh = shortFromBytes(info[i], info[i + 1]);
            int gap = 1;
            for (int j = 0; j < 5; j++) {
                int parity = 0;
                for (int k = gap - 1; k < 16; k += gap * 2) {
                    for (int l = 0; l < gap; l++) {
                        parity ^= (sh >> k + l & 1);//((sh >> k + l) & 1) == 1 ? 1 : 0;
                    }
                }
                control[c] |= parity == 0 ? 0 : 1 << j;
                gap *= 2;
            }
            c++;
        }
        print(info, control);
        return addControlBits(info, control);
    }

    private static void print(byte[] info, byte[] control) {
        for (int i = 0, p = 0; i < info.length; i +=2, p++) {
            System.out.printf("%d, %d; %s+%s (%s)\n",info[i], info[i + 1], printByte(info[i]), printByte(info[i + 1]), printByte(control[p]));
        }
    }

    private static String printByte(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public void addNoise(byte[] b) {
        for (int i = 0; i < b.length * 2 / 3; i += 2) {
            // add noise to one of two bytes
            int r = 0;//new Random().nextInt(1);
            int p = 4;//new Random().nextInt(8);
            b[i + r] ^= 1 << p; // XOR the bit on random position
            System.out.printf("Added noise for byte %d for bit %d\n", i + r, p);
        }
    }

    public String decode(byte[] b) {
        byte[] info = Arrays.copyOfRange(b, 0, b.length * 2 / 3);
        byte[] control = Arrays.copyOfRange(b, b.length * 2 / 3, b.length);

        int c = 0;
        for (int i = 0; i < info.length; i += 2) {
            short sh = shortFromBytes(info[i], info[i + 1]);
            int gap = 1;
            int wrong = 0;
            for (int j = 0; j < 5; j++) {
                int parity = 0;
                for (int k = gap - 1; k < 16; k += gap * 2) {
                    for (int l = 0; l < gap; l++) {
                        parity ^= (sh >> k + l & 1);//((sh >> k + l) & 1) == 1 ? 1 : 0;
                    }
                }
                int mask = parity == 0 ? 0 : 1 << j;
                if ((control[c] & mask) != mask) {
                    wrong += gap;
                }
                gap *= 2;
            }
            if (wrong > 0) {
                wrong -= 1;
                if (wrong > 7) {
                    info[i + 1] |= 1 << (wrong % 7);
                    System.out.printf("Got wrong bit on position %d (%d), correcting it\n", i + 1, wrong % 7);
                }
                else {
                    info[i] |= 1 << wrong;
                    System.out.printf("Got wrong bit on position %d (%d), correcting it\n", i, wrong);
                }
            }
            c++;
        }
        print(info, control);
        return new String(info);
    }

    private byte[] addControlBits(byte[] bytes, byte[] control) {
        byte[] result = Arrays.copyOf(bytes, bytes.length + control.length);
        for (int i = 0; i < control.length; i++) {
            result[bytes.length + i] = control[i];
        }
        return result;
    }

    private static short shortFromBytes(byte b1, byte b2) {
        short res = 0;
        res |= b1;
        res <<= 8;
        res |= b2;
        return res;
    }

    public static void main(String[] args) {
        Hemming hemming = new Hemming();
        byte[] b = hemming.encode("ha");
        hemming.addNoise(b);
        System.out.println(hemming.decode(b));
    }
}

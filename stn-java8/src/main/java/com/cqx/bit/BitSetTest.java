package com.cqx.bit;

import java.util.BitSet;

public class BitSetTest {

    public static void main(String[] args) {
//        test0();
        test1();
    }

    private static void test1() {
        String a = "a";
        char[] chars = a.toCharArray();
        char aChar = chars[1];
        Integer c = 199;
        String s = Integer.toBinaryString(c);
        System.out.println(s);

    }

    private static void test0() {
        BitSet bitSet = new BitSet(10);
        bitSet.set(0, 100, true);
        System.out.println(bitSet.get(20));
        BitSet bitSet1 = new BitSet();
        bitSet1.set(20, true);
        bitSet.and(bitSet1);
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(20));
    }
}

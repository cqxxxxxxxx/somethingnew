package com.cqx.common;

import org.junit.Test;

/**
 * Created by BG307435 on 2018/1/24.
 */
public class IntegerTest {

    @Test
    public void test1() {
        Integer a = 100;
        Integer a1 = 100;
        Integer b = new Integer(100);
        Integer b1 = new Integer(100);
        Integer c = 1333;
        Integer c1 = 1333;
        System.out.println(a == a1);
        System.out.println(b == b1);
        System.out.println(c == c1);
        System.out.println(a.equals(a1));
        System.out.println(b.equals(b1));
        System.out.println(c.equals(c1));
        System.out.println(new Integer(10000) == 10000);
    }
}

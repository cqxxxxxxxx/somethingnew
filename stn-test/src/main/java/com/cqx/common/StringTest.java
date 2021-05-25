package com.cqx.common;

public class StringTest {

    public static void main(String[] args) {

        System.out.println("abc" == "abc");
        System.out.println("abc".equals("abc"));
        System.out.println(System.identityHashCode(new String("abc")));
        System.out.println(System.identityHashCode(new String("abc")));
        System.out.println(System.identityHashCode(new String("abc").intern()));
        System.out.println(System.identityHashCode("abc"));

    }
}

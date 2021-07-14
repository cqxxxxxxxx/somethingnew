package com.cqx.compile;

/**
 * java 值传递
 */
public class PassByValue {

    public static void swap(Integer a, Integer b) {
        a = b;
        b = 1;
    }

    public static void main(String[] args) {
        Integer boy = 1;
        Integer girl = 2;
        swap(boy, girl);
        System.out.println(boy);
        System.out.println(girl);
    }
}

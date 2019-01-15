package com.cqx;

import org.junit.Test;

import java.nio.channels.SelectionKey;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    @Test
    public void test0() {
        System.out.println(1 << 0);
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
        System.out.println(SelectionKey.OP_WRITE);
    }
}

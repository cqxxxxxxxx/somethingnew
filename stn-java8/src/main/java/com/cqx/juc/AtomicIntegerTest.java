package com.cqx.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/23
 */
public class AtomicIntegerTest {

    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        System.out.println(atomicInteger.get());
        atomicInteger.set(10);
        atomicInteger.lazySet(11);
    }
}

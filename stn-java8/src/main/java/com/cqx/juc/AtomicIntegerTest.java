package com.cqx.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/23
 */
public class AtomicIntegerTest {

    static AtomicInteger atomicInteger = new AtomicInteger();

    static AtomicStampedReference<Object> atomicStampedReference = new AtomicStampedReference<Object>(null, 1);

    static AtomicReference<Object> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        System.out.println(atomicInteger.get());
        atomicInteger.set(10);
        atomicInteger.lazySet(11);
    }
}

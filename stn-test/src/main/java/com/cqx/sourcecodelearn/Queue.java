package com.cqx.sourcecodelearn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Queue {

    public static void main(String[] args) {
        new SynchronousQueue<>();
        new LinkedBlockingQueue<>();
        new ArrayBlockingQueue<>(10);
    }
}

package com.cqx.chapter6.concurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 向 {@link ConcurrentLinkedDeque}中添加
 * Created by Shan on 2017/2/25.
 */
public class AddTask implements Runnable{

    private ConcurrentLinkedDeque<String> deque;

    public AddTask(ConcurrentLinkedDeque deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            deque.add(name + ": Element " + i);
        }
    }
}

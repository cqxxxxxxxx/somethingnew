package com.cqx.chapter6.concurrentLinkedDeque;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 从 {@link ConcurrentLinkedDeque}中取出数据
 * Created by Shan on 2017/2/25.
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedDeque<String> deque;

    public PollTask(ConcurrentLinkedDeque deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            deque.pollFirst();
            deque.pollLast();
        }
    }
}

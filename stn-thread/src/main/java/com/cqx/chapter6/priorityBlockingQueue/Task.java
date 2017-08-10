package com.cqx.chapter6.priorityBlockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Shan on 2017/2/25.
 */
public class Task implements Runnable {

    private int id; //task的编号
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}

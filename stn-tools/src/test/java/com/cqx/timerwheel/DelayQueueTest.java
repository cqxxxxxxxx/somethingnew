package com.cqx.timerwheel;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        DelayQueue<Task> delayQueue = new DelayQueue<>();
        delayQueue.add(new Task(now + 5000));
        delayQueue.add(new Task(now + 10000));
        delayQueue.add(new Task(now + 15000));

        while (true) {
            Task task = delayQueue.take();
            System.out.println(System.currentTimeMillis() + ":" + task.startTime);
        }

    }

    private static class Task implements Delayed {
        public long startTime;

        public Task(long s) {
            this.startTime = s;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return startTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
//            Long.valueOf(startTime).compareTo(o);
            return Long.compare(this.getDelay(TimeUnit.MICROSECONDS), o.getDelay(TimeUnit.MICROSECONDS));
        }
    }

}

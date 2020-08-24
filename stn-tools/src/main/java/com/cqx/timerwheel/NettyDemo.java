package com.cqx.timerwheel;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class NettyDemo {

    public static void main(String[] args) {

//        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();

        DelayQueue delayQueue = new DelayQueue();

    }

    private static class DelayTask implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}

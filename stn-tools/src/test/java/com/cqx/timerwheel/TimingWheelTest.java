package com.cqx.timerwheel;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class TimingWheelTest {

    @Test
    public void test() throws InterruptedException {
        long now = System.currentTimeMillis();
        long startTime = now + 10000;
        DelayQueue<TaskList> delayQueue = new DelayQueue<>();
        TimingWheel timingWheel = new TimingWheel(1000, 20, startTime, delayQueue);
        run(delayQueue);
        TimerTask timerTask = new TimerTask(startTime + 1500);
        TimerTask timerTask2 = new TimerTask(startTime + 2500);
        timingWheel.add(timerTask);
        timingWheel.add(timerTask2);
        TimeUnit.SECONDS.sleep(100);
    }

    private void run(DelayQueue<TaskList> delayQueue) {
        new Thread(() -> {
            while (true) {
                TaskList poll = null;
                try {
                    poll = delayQueue.take();
                } catch (InterruptedException e) {
                    continue;
                }
                if (poll == null) {
                    continue;
                }
                TimerTask task;
                while ((task = poll.getAndDel()) != null) {
                    task.run();
                }
            }
        }).start();
    }
}

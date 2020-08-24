package com.cqx.timerwheel;


import java.util.LinkedList;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TaskList implements Delayed {
    private long expiration;

    private LinkedList<TimerTask> tasks = new LinkedList<>();

    public void add(TimerTask timerTask) {
        this.expiration = timerTask.delayMs;
        tasks.offer(timerTask);
    }

    public TimerTask getAndDel() {
        return tasks.pollFirst();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expiration - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MICROSECONDS), o.getDelay(TimeUnit.MICROSECONDS));
    }
}

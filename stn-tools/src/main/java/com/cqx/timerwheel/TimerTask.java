package com.cqx.timerwheel;

import lombok.Getter;

@Getter
public class TimerTask implements Runnable {
    public long delayMs;

    public TimerTask(long delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis() + ":" + this.hashCode());
    }
}

package com.cqx.chapter7.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shan on 2017/2/27.
 */
public class Task implements Runnable {

    private MyLock lock;
    private String name;

    public Task(MyLock lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.printf("Task: %s: Take the lock\n", name);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

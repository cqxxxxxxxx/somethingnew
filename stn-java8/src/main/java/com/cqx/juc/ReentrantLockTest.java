package com.cqx.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/21
 */
public class ReentrantLockTest {

    public static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            lock.lock();
            lock.lock();
            System.out.println(Thread.currentThread() + "locked");
//            lock.unlock();
        }, "wshindie");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        lock.lock();
        System.out.println(Thread.currentThread() + "locked");
//        lock.unlock();
    }
}




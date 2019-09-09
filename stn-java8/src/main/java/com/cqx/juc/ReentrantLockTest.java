package com.cqx.juc;

import org.junit.Test;

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


    @Test
    public void test0() throws InterruptedException {
        lock.lock();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
        TimeUnit.SECONDS.sleep(100);
        lock.unlock();
    }

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




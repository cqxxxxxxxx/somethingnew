package com.cqx.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cqx
 */
public class InterruptTest {
    private static final Object lock = new Object();

    public static void waitInterrupt() {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("exec wait release lock");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("interrupt");
                }
                System.out.println("exec end");
            }
        });
        thread.start();
        thread.interrupt();
    }

    public static void sleepInterrupt() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("exec sleep");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt");
            }
            System.out.println("exec end");
        });
        thread.start();
        thread.interrupt();
    }

    public static void joinInterrupt() {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                System.out.println("exec join 等待主线程完成");
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 跳过等待主线程");
            }
        });
        thread.start();
        thread.interrupt();
    }

    public static void parkInterrupt() {
        Thread thread = new Thread(() -> {
            System.out.println("进入 park阻塞当前线程");
            LockSupport.park();
            System.out.println("interrupt 被打断");
        });
        thread.start();
        thread.interrupt();
    }

    public static void reentrantLockInterrupt() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        Thread thread = new Thread(() -> {
            System.out.println("lock 阻塞开始");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 被打断");
            }
        });
        thread.start();
        thread.interrupt();
        TimeUnit.SECONDS.sleep(5);
        lock.unlock();
        System.out.println("main 释放锁");
    }

    public static void main(String[] args) throws InterruptedException {
//        waitInterrupt();
//        sleepInterrupt();
//        joinInterrupt();
//        parkInterrupt();
        reentrantLockInterrupt();
        TimeUnit.SECONDS.sleep(10);


    }
}

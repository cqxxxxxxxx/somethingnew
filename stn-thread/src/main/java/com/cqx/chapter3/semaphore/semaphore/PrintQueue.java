package com.cqx.chapter3.semaphore;

import java.util.concurrent.Semaphore;

/**
 * semaphore 信号量 大于0代表有可以使用的资源
 * Created by cqx on 2017/2/20.
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        semaphore = new Semaphore(1);   //设置只有1个资源 BinarySemaphore-> 只有 0 1
    }

    public void printJob(Object document){
        try {
            semaphore.acquire();
            long duration = (long) (Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

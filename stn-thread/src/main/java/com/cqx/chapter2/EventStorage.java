package com.cqx.chapter2;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 生产者 消费者问题
 * Created by Shan on 2017/2/18.
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    /**
     * 存入东西
     */
    public synchronized void set() {
        //当列表满的时候 调用wait()挂起线程，让出锁，等待空余空间出现
        while (storage.size() == maxSize) {
            try {
                System.out.println("Set begin waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n", storage.size());
        notifyAll();    //唤醒所有因调用wait()进入休眠的线程
    }

    /**
     * 消费东西
     */
    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                System.out.println("Get begin waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
        notifyAll();
    }
}

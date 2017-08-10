package com.cqx.chapter7.lock;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * {@link PriorityBlockingQueue} 阻塞式线程安全列表 根据添加元素的comparable接口的实现来决定插入元素的位置
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {

        MyLock lock = new MyLock();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(lock, "Task-" + i);
            Thread thread = new Thread(task);
            thread.start();
        }

        boolean value;
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value) {
                    System.out.printf("Main: Trying to get the Lock\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);

        System.out.printf("Main: Got the lock\n");
        lock.unlock();
        System.out.printf("Main: End of the program\n");
    }
}

package com.cqx.chapter6.priorityBlockingQueue;


import java.util.concurrent.PriorityBlockingQueue;

/**
 * {@link PriorityBlockingQueue} 阻塞式线程安全列表 根据添加元素的comparable接口的实现来决定插入元素的位置
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i, queue);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());  //queue的实际大小
        for (int i = 0; i < threads.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program.");
    }
}

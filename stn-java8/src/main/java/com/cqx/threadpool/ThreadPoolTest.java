package com.cqx.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest {

    public static void threadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                10,
                1000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadFactory() {
                    private final AtomicInteger id = new AtomicInteger();
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, id.getAndIncrement() + "Thread");
                    }
                },
                //失败则直接在调用线程里面run
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.submit(() -> {
                System.out.println(finalI);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void scheduled() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            ScheduledFuture<?> schedule = scheduledThreadPoolExecutor.schedule(() -> {
                System.out.println(finalI);
            }, 3, TimeUnit.SECONDS);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
//        threadPool();
//        scheduled();
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 29));
        System.out.println("11111111111111111111111111111111".length());
    }
}

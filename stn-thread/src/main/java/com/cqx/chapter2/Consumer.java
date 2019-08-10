package com.cqx.chapter2;

import java.util.concurrent.Executors;

/**
 * Created by Shan on 2017/2/18.
 */
public class Consumer implements Runnable {
    private EventStorage eventStorage;
public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            eventStorage.get();
        }
    }
}

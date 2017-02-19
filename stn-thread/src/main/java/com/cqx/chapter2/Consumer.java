package com.cqx.chapter2;

/**
 * Created by Shan on 2017/2/18.
 */
public class Consumer implements Runnable {
    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage){
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.get();
        }
    }
}

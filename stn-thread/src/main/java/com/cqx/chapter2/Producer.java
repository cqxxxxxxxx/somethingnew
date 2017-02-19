package com.cqx.chapter2;

/**
 * 生产者类
 * Created by Shan on 2017/2/18.
 */
public class Producer implements Runnable{
    private EventStorage eventStorage;

    public Producer(EventStorage eventStorage){
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.set();
        }
    }
}

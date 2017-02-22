package com.cqx.chapter4;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shan on 2017/2/22.
 */
public class Task implements Runnable{
    private Date initDate;
    private String name;

    public Task(String name){
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on %s\n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on %s\n", Thread.currentThread().getName(), name, initDate);

        try {
            Long duration = (long)(Math.random()*10);
            System.out.printf("%s: Doing a Task during %d seconds\n", Thread.currentThread().getName(), duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: Task Finished on %s\n", Thread.currentThread().getName(), new Date());

    }
}

package com.cqx.chapter3.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch用于等待多个并发事件的完成，然后执行某些任务
 * CountDownLatch 只是同步的辅助类，本身并不能使方法同步，只是他的一些方法是原子性的。
 * Created by cqx on 2017/2/20.
 */
public class VideoConference implements Runnable {

    private final CountDownLatch controller;

    public VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    /**
     * 每一个人进入会议后都会调用该方法,输出未到会者的数量
     *
     * @param name
     */
    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start ... \n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

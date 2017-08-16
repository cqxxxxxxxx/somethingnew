package com.cqx.chapter3.countDownLatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/20.
 */
public class Participant implements Runnable {
    private VideoConference videoconference;
    private String name;

    public Participant(VideoConference videoconference, String name) {
        this.videoconference = videoconference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoconference.arrive(name);
    }
}

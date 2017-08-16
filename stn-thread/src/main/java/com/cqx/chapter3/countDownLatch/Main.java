package com.cqx.chapter3.countDownLatch;

/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        VideoConference videoconference = new VideoConference(10);
        Thread threadConference = new Thread(videoconference);

        threadConference.start();    //回忆开始
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(videoconference, "Participant" + i);
            Thread t = new Thread(participant);
            t.start();
        }
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        threadConference.start();    //回忆开始

    }
}

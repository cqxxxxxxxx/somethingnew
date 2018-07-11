package com.cqx.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2018/3/8.
 */
public class Demo extends Thread {

    public boolean flag;
    CountDownLatch latch = new CountDownLatch(1);

    public static void main(String args[]) throws InterruptedException {
        Demo demo = new Demo();
        demo.start();
        TimeUnit.SECONDS.sleep(2);
        demo.flag = true;
        demo.latch.await();
    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println(flag);
            if (flag) {
                latch.countDown();
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

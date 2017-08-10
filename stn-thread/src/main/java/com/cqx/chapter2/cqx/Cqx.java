package com.cqx.chapter2.cqx;

import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/19.
 */
public class Cqx {


    public void ccc() {
        System.out.println("normal method");
    }

    public void saySomething() {
        synchronized (this) {
            System.out.println("Start sleeping");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Wake up");
        }
    }
}

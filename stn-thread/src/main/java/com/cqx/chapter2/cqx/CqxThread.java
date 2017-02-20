package com.cqx.chapter2.cqx;

import java.util.concurrent.TimeUnit;

/**
 * Created by cqx on 2017/2/19.
 */
public class CqxThread implements Runnable{

    private Cqx cqx;

    public CqxThread(Cqx cqx){
        this.cqx = cqx;
    }

    @Override
    public void run() {
       cqx.saySomething();
    }
}

package com.cqx.chapter2.cqx;

/**
 * Created by cqx on 2017/2/19.
 */
public class CqxThread2 implements Runnable{

    private Cqx cqx;

    public CqxThread2(Cqx cqx){
        this.cqx = cqx;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            cqx.ccc();
        }
    }
}


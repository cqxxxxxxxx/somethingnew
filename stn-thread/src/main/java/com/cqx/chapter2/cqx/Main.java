package com.cqx.chapter2.cqx;

/**
 * 线程获取对象的一个锁后，其他线程可以运行该对象的非同步方法。-> Cqx类中 cc 方法为普通方法
 * <p>
 * Created by cqx on 2017/2/19.
 */
public class Main {

    public static void main(String[] args) {
        Cqx cqx = new Cqx();
        Thread thread1 = new Thread(new CqxThread(cqx));
        Thread thread2 = new Thread(new CqxThread2(cqx));

        thread1.start();
        thread2.start();
    }
}

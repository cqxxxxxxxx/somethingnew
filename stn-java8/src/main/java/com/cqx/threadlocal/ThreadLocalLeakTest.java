package com.cqx.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/24
 */
public class ThreadLocalLeakTest {

    public static ThreadLocal<String> omg = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor
        omg.set("wsnd");
        omg.get();
        Thread newT = new Thread(() -> omg.set("gugugu"));
        newT.start();

        TimeUnit.SECONDS.sleep(10000);

    }
}

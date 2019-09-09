package com.cqx.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * A、B、C三个线程
 * 开始时全部阻塞
 * 然后全部放开执行打印一句话
 * 然后继续阻塞等待其他线程完成后接着执行
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/1
 */
public class Test1 {

    public static final CountDownLatch latch1 = new CountDownLatch(3);
    public static final CountDownLatch latch2 = new CountDownLatch(3);
    public static final Object lock = new Object();
    public static final ReentrantLock reentrantLock = new ReentrantLock();

    public static final Function<String, Runnable> runnableFunction = x -> {
        Runnable runnable = () -> {
            try {
                Thread.sleep(10000);
//                latch1.countDown();
//                latch1.await();
                System.out.println("线程" + x + "打印");
                latch2.countDown();
                latch2.await();
                System.out.println("其他线程也好了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        return runnable;
    };

    public static void main(String[] args) {
        Thread A = new Thread(runnableFunction.apply("A"));

        Thread B = new Thread(runnableFunction.apply("B"));

        Thread C = new Thread(runnableFunction.apply("C"));

        A.start();
        B.start();
        C.start();


    }

}

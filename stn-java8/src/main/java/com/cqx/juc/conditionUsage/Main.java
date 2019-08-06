package com.cqx.juc.conditionUsage;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/7/30
 */
public class Main {

    public static class ThreadA implements Runnable {
        private ConditionUsage conditionUsage;

        public ThreadA(ConditionUsage service) {
            this.conditionUsage = service;
        }

        @Override
        public void run() {
            conditionUsage.awaitA();
        }

    }

    public static class ThreadB implements Runnable {
        private ConditionUsage conditionUsage;

        public ThreadB(ConditionUsage service) {
            this.conditionUsage = service;
        }

        @Override
        public void run() {
            conditionUsage.awaitB();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionUsage service = new ConditionUsage();
        Runnable runnable1 = new ThreadA(service);
        Runnable runnable2 = new ThreadB(service);

        new Thread(runnable1, "a").start();
        new Thread(runnable2, "b").start();

        // 线程sleep2秒钟
        Thread.sleep(2000);
        // 唤醒所有持有conditionA的线程
        service.signalA();

        Thread.sleep(2000);
        // 唤醒所有持有conditionB的线程
        service.signalB();
    }
}

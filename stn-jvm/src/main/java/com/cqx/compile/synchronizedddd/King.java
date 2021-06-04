package com.cqx.compile.synchronizedddd;

/**
 * Created by BG307435 on 2018/7/26.
 */
public class King {

    private static Object lock = new Object();

    public synchronized void sayHi(String xxx) {
        System.out.println(xxx);
        System.out.println("hi");
    }

    public void sayHi() {
        synchronized (lock) {
            System.out.println("hi");
        }
    }

    public void wait_notify() throws InterruptedException {
        lock.wait();
        lock.notify();
    }

    public static void main(String[] args) throws InterruptedException {
        lock.wait();
        lock.notify();
    }
}

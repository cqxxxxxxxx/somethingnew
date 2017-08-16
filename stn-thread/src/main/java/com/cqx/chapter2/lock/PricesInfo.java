package com.cqx.chapter2.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Shan on 2017/2/19.
 */
public class PricesInfo {

    private double price1;
    private double price2;
    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.println("获取了锁");
        try {
            TimeUnit.SECONDS.sleep(20);     //休眠20s，不释放锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }
}

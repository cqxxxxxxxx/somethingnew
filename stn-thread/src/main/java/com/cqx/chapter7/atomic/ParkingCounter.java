package com.cqx.chapter7.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义的原子对象
 * 用于记录停车场停车数据的的atomic对象
 * Created by Shan on 2017/2/27.
 */
public class ParkingCounter extends AtomicInteger {

    private int maxNumber;

    public ParkingCounter(int maxNumber) {
        set(0);
        this.maxNumber = maxNumber;
    }

    public boolean carIn() {
        for (; ; ) {
            int value = get();
            if (value == maxNumber) {
                System.out.printf("ParkingCounter: The parking lot is full.\n");
                return false;
            } else {
                int newValue = value + 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has entered.\n");
                    return true;
                }
            }
        }
    }

    public boolean carOut() {
        for (; ; ){
            int value = get();
            if (value == 0){
                System.out.printf("ParkingCounter: The parking lot is empty.\n");
                return false;
            }else {
                int newValue = value - 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed){
                    System.out.printf("ParkingCounter: A car has gone out.\n");
                    return true;
                }
            }
        }
    }
}

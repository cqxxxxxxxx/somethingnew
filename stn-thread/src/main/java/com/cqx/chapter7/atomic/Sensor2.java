package com.cqx.chapter7.atomic;

/**
 * 传感器2号
 * Created by Shan on 2017/2/27.
 */
public class Sensor2 implements Runnable {

    private ParkingCounter counter;

    public Sensor2(ParkingCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carOut();
    }
}

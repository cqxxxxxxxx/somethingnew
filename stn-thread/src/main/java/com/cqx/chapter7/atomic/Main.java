package com.cqx.chapter7.atomic;


import java.util.concurrent.ExecutionException;


/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ParkingCounter counter = new ParkingCounter(5); //创建个原子对象，设置最多5辆车好停
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);

        Thread thread1 = new Thread(sensor1);
        Thread thread2 = new Thread(sensor2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Main: Number of cars: %d\n", counter.get()); //输出最终的counter值
        System.out.println("Main: End of the program.");

    }
}

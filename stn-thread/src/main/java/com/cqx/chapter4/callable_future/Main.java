package com.cqx.chapter4.callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by cqx on 2017/2/20.
 */
public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> results = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactorialCaculator caculator = new FactorialCaculator(number);
            Future<Integer> future = executor.submit(caculator);
            results.add(future);
        }

        do {
            System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
            for (int i = 0; i < 10; i++) {
                System.out.printf("Main: Task %d: %s\n", i, results.get(i).isDone());
            }
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < 10);

        System.out.println("Main: Results");
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = results.get(i);
            Integer number = null;
            try {
                number = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main: Task %d: %d\n", i, number);
        }
    }
}

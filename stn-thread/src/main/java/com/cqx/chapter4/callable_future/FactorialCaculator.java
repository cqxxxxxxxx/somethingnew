package com.cqx.chapter4.callable_future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shan on 2017/2/22.
 */
public class FactorialCaculator implements Callable<Integer> {

    private Integer number;

    public FactorialCaculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;

        if (number == 0 || number == 1) {
            return 1;
        } else {
            for (int i = 2; i < number; i++) {
                result *= i;
                TimeUnit.SECONDS.sleep(20);
            }
        }

        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
        return result;
    }
}

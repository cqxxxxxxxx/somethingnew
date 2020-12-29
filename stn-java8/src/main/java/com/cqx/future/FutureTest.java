package com.cqx.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask future = new FutureTask<String>(() -> {
            return "1";
        });
        new Thread(future).start();
        final Object o = future.get();
        System.out.println(o);
    }
}

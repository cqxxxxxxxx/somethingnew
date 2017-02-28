package com.cqx.chapter7.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * Created by Shan on 2017/2/27.
 */
public class MyWorkThread extends ForkJoinWorkerThread {


    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();


    /**
     * Creates a ForkJoinWorkerThread operating in the given pool.
     *
     * @param pool the pool this thread works in
     * @throws NullPointerException if pool is null
     */
    protected MyWorkThread(ForkJoinPool pool) {
        super(pool);
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
        taskCounter.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    public void addTask() {
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }
}

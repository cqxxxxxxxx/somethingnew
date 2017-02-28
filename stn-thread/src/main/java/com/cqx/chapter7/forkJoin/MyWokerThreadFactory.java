package com.cqx.chapter7.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * Created by Shan on 2017/2/27.
 */
public class MyWokerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory{
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkThread(pool);
    }
}

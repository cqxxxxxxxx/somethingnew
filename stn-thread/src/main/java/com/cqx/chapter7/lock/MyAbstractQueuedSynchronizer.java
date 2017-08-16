package com.cqx.chapter7.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by Shan on 2017/2/27.
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

    private AtomicInteger state;

    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}

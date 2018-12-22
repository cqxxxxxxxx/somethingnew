package com.cqx.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by BG307435 on 2018/2/26.
 */
public class ThreadPoolDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    @Test
    public void singleThreadExecutor() {
        ExecutorService executorService0 = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        System.out.println(Integer.toBinaryString(CAPACITY));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(RUNNING));
        System.out.println(Integer.toBinaryString(SHUTDOWN));
    }


    @Test
    public void finallyTest() {
        Throwable throwable = null;
        try {
            try {
                throw new RuntimeException("aaa");
            } catch (RuntimeException e) {
                throwable = e;
                throw e;
            } finally {
                System.out.println("inner" + throwable);
            }
        } finally {
            System.out.println("outer" + throwable);
        }
    }

}

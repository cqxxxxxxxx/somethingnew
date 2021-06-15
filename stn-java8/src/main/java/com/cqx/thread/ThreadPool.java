package com.cqx.thread;

import java.util.concurrent.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/6
 */
public class ThreadPool {

    ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.DAYS,
            new LinkedBlockingDeque<>());

    ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    ThreadPoolExecutor e = Executors.newCachedThreadPool().submit()
}

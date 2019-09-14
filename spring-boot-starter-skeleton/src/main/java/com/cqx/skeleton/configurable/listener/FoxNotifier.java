package com.cqx.skeleton.configurable.listener;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * fox框架的事件通知器
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
public class FoxNotifier {

    /**
     * 排序比较器
     */
    private static final Comparator<FoxListener> FOX_LISTENER_COMPARATOR = (a, b) -> {
        return Integer.compare(a.getOrder(), b.getOrder());
    };

    /**
     * 异步通知的线程池
     */
    private static final ExecutorService NOTIFY_EXECUTOR =
            new ThreadPoolExecutor(5, 10,
                    60, TimeUnit.SECONDS,
                    //阻塞队列
                    new LinkedBlockingDeque<>(),
                    //线程工厂类
                    r -> new Thread(r, "fox-notifier-thread"),
                    //拒绝策略
                    new ThreadPoolExecutor.AbortPolicy());

    private Lock lock = new ReentrantLock();

    /**
     * 监听器 用order排序过的
     */
    private TreeSet<FoxListener> foxListenerTreeSet;

    public boolean addEventListener(FoxListener foxListener) {
        lock.lock();
        try {
            foxListenerTreeSet.add(foxListener);
        } finally {
            lock.unlock();
        }
        return true;
    }

    public boolean removeEventListener(FoxListener foxListener) {
        lock.lock();
        try {
            foxListenerTreeSet.remove(foxListener);
        } finally {
            lock.unlock();
        }
        return true;
    }

    /**
     * @param foxEvent
     */
    public void notifyAll(FoxEvent foxEvent) {
        lock.lock();
        try {
            for (FoxListener listener : foxListenerTreeSet) {
                if (listener.isAsync()) {
                    NOTIFY_EXECUTOR.submit(() -> listener.onEvent(foxEvent));
                } else {
                    listener.onEvent(foxEvent);
                }
            }
        } finally {
            lock.unlock();
        }
    }

}

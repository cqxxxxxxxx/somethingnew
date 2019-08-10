package com.cqx.stncqxhat.plugin.impl;

import com.cqx.Meta;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import com.google.auto.service.AutoService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Slf4j
@AutoService(Plugin.class)
@Meta(mode = 2, pluginName = "chat plugin")
public class ChatPlugin extends AbstractPlugin {

    /**
     * chat的具体实现
     */
    private ChatService chatService;

    /**
     * 是否已经初始化过了
     */
    private volatile boolean initialized = false;

    /**
     * 初始化用的锁
     */
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * 主动阻塞时间 1000毫秒
     */
    private static final long PARK_NANOSECONDS = 1000 * 1000;

    /**
     * 自旋的次数
     * 可用处理器小于2 -> 0
     * 否则 32
     */
    private static final int SPIN_TIMES = (Runtime.getRuntime().availableProcessors() < 2) ? 0 : 32;

    @Override
    public void act(Message message) {
        if (!initialized) {
            doInitialize0();
        }
        chatService.say(message);
    }

    /**
     * 正确版本
     * 阻止并发的初始化
     * 虽然并发初始化也没问题，因为幂等
     *
     * 关于Thread.sleep与LockSupport的选择
     * 1. lockSupport支持unPark 可能会被其他线程unPark而恢复
     * 2. sleep字面意思更加明显
     * https://stackoverflow.com/questions/10397881/java-locksupport-parknanos-vs-thread-sleep
     */
    private void doInitialize0() {
        if (lock.tryLock()) {
            try {
                chatService = ApplicationContextUtil.getBean(ChatService.class);
                initialized = true;
            } finally {
                lock.unlock();
            }
        } else {
            //锁获取失败则自旋等待其他线程初始化好就好了
            for (;;) {
                if (initialized) {
                    return;
                }
                //让当前线程等待一段时间 放弃cpu
                LockSupport.parkNanos(this, PARK_NANOSECONDS);
            }
        }
    }

    /**
     * 自旋锁的实现版本
     * 这个版本用不到，因为不需要每条线程都去初始化
     */
    private void doInitialize() {
        if (lock.tryLock()) {
            try {
                chatService = ApplicationContextUtil.getBean(ChatService.class);
                initialized = true;
            } finally {
                lock.unlock();
            }
        } else {
            /**
             * 进入自旋等待
             *
             * 反正就是要等到到上面初始化完成
             */
            int spinTimes = 0;
            for (;;) {
                if (spinTimes++ > SPIN_TIMES) {
                    doInitialize();
                }
            }
        }
    }
}

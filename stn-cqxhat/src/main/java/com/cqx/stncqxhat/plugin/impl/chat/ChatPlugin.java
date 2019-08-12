package com.cqx.stncqxhat.plugin.impl.chat;

import com.cqx.Meta;
import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.cqx.stncqxhat.support.keywords.KeyWord;
import com.cqx.stncqxhat.support.keywords.PrintHandler;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Component
@Slf4j
@AutoService(Plugin.class)
@Meta(mode = 2, pluginName = "chat plugin")
public class ChatPlugin extends AbstractPlugin {

    /**
     * chat的具体实现
     */
    @Deprecated
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private BroadcastChatHandler broadcastChatHandler;
    @Autowired
    private PersonalChatHandler personalChatHandler;

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
    public void initialize() {
        super.initialize();
        keyWordPool
                .withDefault("default", "默认行为处理器", PrintHandler.getInstance())
                .addKeyWord("-broadcast", "广播", broadcastChatHandler)
                .addKeyWord("-personal", "私人", personalChatHandler);
    }

    @Override
    public void act(Message m) {
        String msg = m.getMsg();
        User currentUser = userService.currentUser();
//关键字
        if (keyWordPool.isKeyWords(msg)) {
            currentUser.setSubKeyWord(msg);
            userService.update(currentUser);
            ChannelContext.writeAndFlush(Message.of(ServerConst.SYSTEM_USER, "switch success, you are now in " + msg));
            return;
        }
        if (keyWordPool.isKeyWords(currentUser.getSubKeyWord())) {
            KeyWord keyWord = keyWordPool.get(currentUser.getSubKeyWord());
            keyWord.getHandler().handle(m);
            return;
        }
//默认处理器
        m.setMsg(keyWordPool.getPool()
                .values()
                .stream()
                .map(x -> {
                    return x.getKey() + "  介绍:" + x.getInfo();
                })
                .collect(Collectors.joining("\n")).trim());
        keyWordPool.defaultKeyWord().handle(m);
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
    @Deprecated
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
    @Deprecated
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

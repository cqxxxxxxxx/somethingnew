package com.cqx.cqxhat.service;

import com.cqx.cqxhat.handler.listener.FailureListener;
import com.cqx.cqxhat.model.User;
import com.cqx.cqxhat.support.cache.CachePool;
import com.cqx.cqxhat.support.cache.UserPool;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class ChatServiceImpl implements ChatService {

    private CachePool<String, User> userPool = UserPool.getInstance();

    @Override
    public void join(User user) {
        userPool.put(user.getName(), user);
    }

    @Override
    public void leave(String name) {
        userPool.evict(name);
    }

    @Override
    public void say(String msg) {
        List<User> users = userPool.getAll();
        // 发送给每一个狗腿子
        users.stream().forEach(x -> {
            x.getChannel().writeAndFlush(msg).addListener(FailureListener.getInstance());
        });
    }

    @Override
    public List<User> getUserAll() {
        return userPool.getAll();
    }
}

package com.cqx.stncqxhat.service;

import com.cqx.stncqxhat.handler.listener.FailureListener;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.support.cache.CachePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private CachePool<String, User> userPool;

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

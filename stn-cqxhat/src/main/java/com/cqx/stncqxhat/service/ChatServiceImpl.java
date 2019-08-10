package com.cqx.stncqxhat.service;

import com.cqx.stncqxhat.handler.listener.FailureListener;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.support.cache.CachePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    private CachePool<String, User> userPool;

    @Override
    public void say(Message message) {
        List<User> users = userPool.getAll();
        // 发送给每一个狗腿子
        users.stream().forEach(x -> {
            x.getChannel().writeAndFlush(message).addListener(FailureListener.getInstance());
        });
    }

}

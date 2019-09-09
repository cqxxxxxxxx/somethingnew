package com.cqx.stncqxhat.service;

import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.support.cache.CachePool;
import com.cqx.stncqxhat.support.core.ChannelContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CachePool<String, User> userPool;

    @Override
    public User currentUser() {
        return userPool.get(defaultUser().getName());
    }

    @Override
    public User defaultUser() {
        Channel channel = ChannelContext.currentChannel().channel();
        InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
        String ip = remote.getAddress().getHostAddress();
        String host = remote.getHostName();
        return User.of(host, ip, ChannelContext.currentChannel(), 0, ServerConst.BLANK);
    }

    @Override
    public void join(User user) {
        Objects.requireNonNull(user, "user不能为空");
        userPool.put(user.getName(), user);
    }

    @Override
    public void leave(String name) {
        Objects.requireNonNull(name, "name不能为空");
        userPool.evict(name);
    }

    @Override
    public User get(String name) {
        Objects.requireNonNull(name, "name不能为空");
        return userPool.get(name);
    }

    @Override
    public void update(User user) {
        Objects.requireNonNull(user, "user不能为空");
        userPool.put(user.getName(), user);
    }

    @Override
    public List<User> getUserAll() {
        return userPool.getAll();
    }


}

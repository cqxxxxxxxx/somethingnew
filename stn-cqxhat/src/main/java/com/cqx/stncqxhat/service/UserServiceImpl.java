package com.cqx.stncqxhat.service;

import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.support.cache.CachePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void join(User user) {
        userPool.put(user.getName(), user);
    }

    @Override
    public void leave(String name) {
        userPool.evict(name);
    }

    @Override
    public List<User> getUserAll() {
        return userPool.getAll();
    }
}

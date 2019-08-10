package com.cqx.stncqxhat.service;

import com.cqx.stncqxhat.model.User;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/5
 */
public interface UserService {

    User currentUser();

    User defaultUser();

    void join(User user);

    void leave(String name);

    User get(String name);

    void update(User user);

    List<User> getUserAll();

}

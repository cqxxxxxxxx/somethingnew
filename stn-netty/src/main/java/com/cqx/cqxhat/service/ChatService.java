package com.cqx.cqxhat.service;

import com.cqx.cqxhat.model.User;

import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public interface ChatService {

    void join(User user);

    void leave(String name);

    void say(String msg);

    List<User> getUserAll();

}

package com.cqx.stncqxhat.plugin;

import com.cqx.stncqxhat.model.Message;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public interface Plugin {

    void act(Message m);

    Metadata metadata();
}

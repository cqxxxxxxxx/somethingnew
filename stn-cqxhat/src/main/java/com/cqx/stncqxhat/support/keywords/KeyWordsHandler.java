package com.cqx.stncqxhat.support.keywords;

import com.cqx.stncqxhat.model.Message;

import java.nio.channels.Channel;

/**
 * 关键字对应的处理器
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
public interface KeyWordsHandler {

    void handle(Message message);

}

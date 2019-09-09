package com.cqx.stncqxhat.plugin.impl.chat;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.keywords.KeyWordsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/12
 */
@Component
@Slf4j
public class PersonalChatHandler implements KeyWordsHandler {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @Override
    public void handle(Message message) {

    }
}

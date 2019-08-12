package com.cqx.stncqxhat.plugin.impl.chat;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.support.keywords.KeyWordsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
@Slf4j
@Component
public class BroadcastChatHandler implements KeyWordsHandler {
    @Autowired
    ChatService chatService;

    @Override
    public void handle(Message message) {
        chatService.say(message);
    }
}

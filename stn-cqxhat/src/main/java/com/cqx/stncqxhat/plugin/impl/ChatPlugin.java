package com.cqx.stncqxhat.plugin.impl;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Metadata;
import com.cqx.stncqxhat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public class ChatPlugin extends AbstractPlugin {

    @Autowired
    private ChatService chatService;

    @Override
    public void act(Message message) {
        chatService.say(message.getMsg());
    }

    @Override
    public Metadata metadata() {
        Metadata metadata = new Metadata();
        metadata.setAuthor("cqx");
        metadata.setMode(shift(2));
        metadata.setPluginName("chat plugin");
        metadata.setVersion("0.1");
        return metadata;
    }
}

package com.cqx.stncqxhat.plugin.impl;

import com.cqx.Meta;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.service.ChatService;
import com.google.auto.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@AutoService(Plugin.class)
@Meta(mode = 2, pluginName = "chat plugin")
public class ChatPlugin extends AbstractPlugin {

    @Autowired
    private ChatService chatService;

    @Override
    public void act(Message message) {
        chatService.say(message.getMsg());
    }

}

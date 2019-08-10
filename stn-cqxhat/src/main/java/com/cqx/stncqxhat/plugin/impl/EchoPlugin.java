package com.cqx.stncqxhat.plugin.impl;

import com.cqx.Meta;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.support.core.ChannelContext;
import com.google.auto.service.AutoService;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@AutoService(Plugin.class)
@Meta(mode = 1, pluginName = "echo plugin", author = "cqx")
public class EchoPlugin extends AbstractPlugin {
    @Override
    public void act(Message message) {
        ChannelContext.writeAndFlush(message);
    }
}

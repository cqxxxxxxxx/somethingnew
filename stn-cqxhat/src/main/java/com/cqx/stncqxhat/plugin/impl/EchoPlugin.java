package com.cqx.stncqxhat.plugin.impl;

import com.cqx.Meta;
import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.ChatTTL;
import io.netty.channel.ChannelHandlerContext;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Meta(mode = 0, pluginName = "echo plugin", author = "cqx")
public class EchoPlugin extends AbstractPlugin {
    @Override
    public void act(Message message) {
        ChannelHandlerContext chc = ChatTTL.get(ServerConst.Keys.CHC);
        chc.writeAndFlush(message.getMsg());
    }
}

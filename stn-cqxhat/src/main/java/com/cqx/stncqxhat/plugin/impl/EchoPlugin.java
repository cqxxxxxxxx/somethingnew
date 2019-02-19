package com.cqx.stncqxhat.plugin.impl;

import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.plugin.AbstractPlugin;
import com.cqx.stncqxhat.plugin.ChatTTL;
import com.cqx.stncqxhat.plugin.Metadata;
import io.netty.channel.ChannelHandlerContext;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public class EchoPlugin extends AbstractPlugin {
    @Override
    public void act(Message message) {
        ChannelHandlerContext chc = ChatTTL.get(ServerConst.Keys.CHC);
        chc.writeAndFlush(message.getMsg());
    }

    @Override
    public Metadata metadata() {
        Metadata metadata = new Metadata();
        metadata.setAuthor("cqx");
        metadata.setMode(0);
        metadata.setPluginName("echo plugin");
        metadata.setVersion("0.1");
        return metadata;
    }
}

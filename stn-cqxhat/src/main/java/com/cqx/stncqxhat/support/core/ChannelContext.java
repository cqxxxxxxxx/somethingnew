package com.cqx.stncqxhat.support.core;

import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
public class ChannelContext {

    private static final ThreadLocal<ChannelHandlerContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static ChannelHandlerContext currentChannel() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    public static void setChannel(ChannelHandlerContext channelHandlerContext) {
        CONTEXT_THREAD_LOCAL.set(channelHandlerContext);
    }

    public static void writeAndFlush(String str) {
        Message message = new Message();
        message.setFrom(ServerConst.SYSTEM_USER);
        message.setMsg(str);
        currentChannel().writeAndFlush(message);
    }

    public static void writeAndFlush(Message message) {
        currentChannel().writeAndFlush(message.getMsg());
    }

    public static void writeAndFlush(Message message, ChannelPromise promise) {
        currentChannel().writeAndFlush(message.getMsg(), promise);
    }
}

package com.cqx.stncqxhat.mock;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/9
 */
@Slf4j
@ChannelHandler.Sharable
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // discard
    }
}
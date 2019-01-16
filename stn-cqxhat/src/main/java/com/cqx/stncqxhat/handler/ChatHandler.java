package com.cqx.stncqxhat.handler;

import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.support.util.ChcUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ChatHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private ChatService chatService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User user = ChcUtil.getUser(ctx);
        chatService.join(user);
        log.info("有崽种连进来了[{}]", user.getName());
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        User user = ChcUtil.getUser(ctx);
        chatService.leave(user.getName());
        log.info("有崽种滚出去了[{}]", user.getName());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
//        ctx.writeAndFlush(body).addListener(FailureListener.getInstance());
        chatService.say(body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.getMessage(), cause);
    }
}

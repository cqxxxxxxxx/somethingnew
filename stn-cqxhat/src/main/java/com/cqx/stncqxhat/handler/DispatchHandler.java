package com.cqx.stncqxhat.handler;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.plugin.PluginUtil;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.core.ChannelContext;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class DispatchHandler extends ChannelInboundHandlerAdapter {
    /**
     * 每个请求都会创建线程来执行，不过会复用之前的线程
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    @Autowired
    private UserService userService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ChannelContext.setChannel(ctx);
        Message message = (Message) msg;
        User cache = userService.currentUser();
        message.setFrom(cache);
        dispatch(message);
    }

    /**
     * 根据当前user所在的插件mode，获取对应插件，然后执行
     * @param message
     */
    private void dispatch(Message message) {
        User user = message.getFrom();
        Plugin p;
        if (PluginUtil.isPluginSwitch(message.getMsg())) {
            p = PluginUtil.getPlugin(message.getMsg());
            user.setMode(p.metadata().getMode());
            userService.update(user);
        } else {
            p = PluginUtil.getPlugin(user.getMode());
//        暂时不用自己的线程池,使用workEventLoopGroup的线程
//        executor.execute(() -> p.act(message));
            p.act(message);
        }
    }


    /**
     * 有连接进来时的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User user = userService.defaultUser();
        userService.join(user);
        log.info("有崽种连进来了[{}]", user.getName());
//      第一次连接上来的处理
        onFirstConnect();
        ctx.fireChannelActive();
    }

    /**
     * 连接断开时的处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        User user = userService.currentUser();
        userService.leave(user.getName());
        log.info("有崽种滚出去了[{}]", user.getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.getMessage(), cause);
    }


    /**
     * 第一次连接进来 发送help的消息
     */
    private void onFirstConnect() {
        Plugin helpPlugin = PluginUtil.getPlugin(0);
        helpPlugin.act(null);
    }

}

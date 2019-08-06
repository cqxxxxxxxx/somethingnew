package com.cqx.stncqxhat.handler;

import com.cqx.stncqxhat.constant.ServerConst;
import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.plugin.ChatTTL;
import com.cqx.stncqxhat.plugin.Plugin;
import com.cqx.stncqxhat.plugin.PluginUtil;
import com.cqx.stncqxhat.service.UserService;
import com.cqx.stncqxhat.support.util.ChcUtil;
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
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            20, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
    @Autowired
    private UserService userService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        User user = message.getFrom();
        String pluginName;
        if ((pluginName = PluginUtil.getPluginName(message.getMsg())) != null) {
            user.setMode(PluginUtil.getMode(pluginName));
            userService.join(user);
        }
        ChatTTL.put(ServerConst.Keys.CHC, ctx);
        user.setChannel(ctx.channel());
        dispatch(message);
    }

    private void dispatch(Message message) {
        User user = message.getFrom();
        Plugin p = PluginUtil.getPlugin(user.getMode());
        executor.execute(() -> p.act(message));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User user = ChcUtil.getUser(ctx);
        userService.join(user);
        log.info("有崽种连进来了[{}]", user.getName());
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        User user = ChcUtil.getUser(ctx);
        userService.leave(user.getName());
        log.info("有崽种滚出去了[{}]", user.getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.getMessage(), cause);
    }

}

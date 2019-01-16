package com.cqx.stncqxhat.support.util;

import com.cqx.stncqxhat.model.User;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class ChcUtil {

    public static User getUser(ChannelHandlerContext ctx) {
        return getUser(ctx.channel());
    }

    public static User getUser(Channel channel) {
        InetSocketAddress remote = (InetSocketAddress) channel.remoteAddress();
        String ip = remote.getAddress().getHostAddress();
        String host = remote.getHostName();
        return User.of(host, ip, channel);
    }
}

package com.cqx.cqxhat.handler.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Slf4j
public class FailureListener implements ChannelFutureListener {
    private static final ChannelFutureListener INSTANCE = new FailureListener();

    private FailureListener() {
    }

    public static ChannelFutureListener getInstance() {
        return INSTANCE;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (!future.isSuccess()) {
            log.error(future.cause().getMessage(), future.cause());
        }
    }
}

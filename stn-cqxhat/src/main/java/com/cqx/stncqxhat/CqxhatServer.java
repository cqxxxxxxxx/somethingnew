package com.cqx.stncqxhat;

import com.cqx.stncqxhat.handler.DispatchHandler;
import com.cqx.stncqxhat.handler.ServerHandler;
import com.cqx.stncqxhat.handler.decoder.DelimiterDecoder;
import com.cqx.stncqxhat.handler.decoder.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
@Slf4j
@Component
public class CqxhatServer implements AutoCloseable {
    private Channel nioServerSocketChannel;
    private EventLoopGroup boss = new NioEventLoopGroup(1);
    private EventLoopGroup work = new NioEventLoopGroup();
    private static AtomicBoolean launched = new AtomicBoolean(false);

    @Autowired
    private ServerHandler serverHandler;
    @Autowired
    private DispatchHandler dispatchHandler;

    public void start(SocketAddress socketAddress) throws InterruptedException {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .handler(new ChannelInitializer<NioServerSocketChannel>() {
                        @Override
                        protected void initChannel(NioServerSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline
                                    .addLast("log", new LoggingHandler())
                                    .addLast(serverHandler);
                        }
                    })
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("log", new LoggingHandler())
                                    .addLast(new DelimiterDecoder())
                                    .addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new MessageEncoder())
                                    .addLast(dispatchHandler);
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_BACKLOG, 128);

            if (launched.compareAndSet(false, true)) {
                ChannelFuture f = b.bind(socketAddress).sync();
                nioServerSocketChannel = f.channel();
                f.channel().closeFuture().sync();
                return;
            }
            log.error("cqxhat already running");
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public boolean launched() {
        return launched.get();
    }

    @Override
    public void close() {
        if (launched.get()) {
            nioServerSocketChannel.close();
            boss.shutdownGracefully();
            work.shutdownGracefully();
            launched.set(false);
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        CqxhatServer cqxhatServer = new CqxhatServer();
//        cqxhatServer.start();
//    }
}

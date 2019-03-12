package com.cqx.stncqxhat.mock;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/9
 */
@Slf4j
public class DiscardClient {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8009"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    static final int MAXGLOBALTHROUGHPUT = Integer.parseInt(System.getProperty("maxGlobalThroughput", "1"));
    static final int MAXCHANNELTHROUGHPUT = Integer.parseInt(System.getProperty("maxChannelThroughput", "1"));
    static final int connectionCount = Integer.parseInt(System.getProperty("connectionCount", "1"));

    /**
     * {@code true} - Use {@link Channel#isWritable()} and
     * {@link ChannelInboundHandler#channelWritabilityChanged(ChannelHandlerContext)},
     * {@code false} - Use {@code writeAndFLuxh(object).addListener(listener)},
     * Default value is {@code false}.
     */
    static final boolean useIsWritable = Boolean.parseBoolean(System.getProperty("useIsWritable", "false"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        final GlobalTrafficShapingHandler gtsh;
        final GlobalChannelTrafficShapingHandlerWithLog gctsh;
        if (MAXGLOBALTHROUGHPUT > 0 && MAXCHANNELTHROUGHPUT > 0) {
            gctsh = new GlobalChannelTrafficShapingHandlerWithLog(group, MAXGLOBALTHROUGHPUT, 0,
                    MAXCHANNELTHROUGHPUT, 0, 1000);
            gtsh = null;
        } else if (MAXGLOBALTHROUGHPUT > 0) {
            gtsh = new GlobalTrafficShapingHandler(group, MAXGLOBALTHROUGHPUT, 0, 1000);
            gctsh = null;
        } else {
            gtsh = null;
            gctsh = null;
        }
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                            }
                            if (MAXGLOBALTHROUGHPUT > 0 && MAXCHANNELTHROUGHPUT > 0) {
                                p.addLast(gctsh);
                            } else if (MAXGLOBALTHROUGHPUT > 0) {
                                p.addLast(gtsh);
                            } else if (MAXCHANNELTHROUGHPUT > 0) {
                                p.addLast(new ChannelTrafficShapingHandler(MAXCHANNELTHROUGHPUT, 0, 1000));
                            }
                            p.addLast(new DiscardClientHandler());
                        }
                    });

            List<ChannelFuture> futures = new LinkedList<ChannelFuture>();
            for (int i = 0; i < connectionCount; i++) {
                // Make the connection attempt.
                futures.add(b.connect(HOST, PORT).sync());
            }
            for (ChannelFuture channelFuture : futures) {
                // Wait until the connection is closed.
                channelFuture.channel().closeFuture().sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
package com.cqx.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/9
 */
public class NettyServerFlow {


    public static void main(String[] args) throws InterruptedException {
        final String host = "127.0.0.1";
        final int port = 8080;
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup acceptorGroup = new NioEventLoopGroup();
        EventLoopGroup ioGroup = new NioEventLoopGroup(10);
        bootstrap.group(acceptorGroup, ioGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("ACTIVE: nio server socket channel  handler");
                        ctx.fireChannelActive();
                    }
                })
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("ACTIVE 0: socket channel  handler");
                                ctx.fireChannelActive();
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ctx.write(msg); // (1)
                                ctx.flush(); // (2)
                            }
                        })
                                .addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println("ACTIVE 1: socket channel  handler");
                                    }

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ctx.write(msg); // (1)
                                        ctx.flush(); // (2)
                                    }

                                });
                    }
                });

        // Bind and start to accept incoming connections.
        ChannelFuture f = bootstrap.bind(port).sync();

        // Wait until the server socket is closed.
        // In this example, this does not happen, but you can do that to gracefully
        // shut down your server.
        f.channel().closeFuture().sync();
        ByteBuf byteBuf = Unpooled.buffer();
    }


}

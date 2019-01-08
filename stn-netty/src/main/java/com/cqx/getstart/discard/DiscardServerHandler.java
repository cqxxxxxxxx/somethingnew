package com.cqx.getstart.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 1. DiscardServerHandler extends ChannelInboundHandlerAdapter, which is an implementation of ChannelInboundHandler. ChannelInboundHandler provides various event handler methods that you can override. For now, it is just enough to extend ChannelInboundHandlerAdapter rather than to implement the handler interface by yourself.
 * 2. We override the channelRead() event handler method here. This method is called with the received message, whenever new data is received from a client. In this example, the type of the received message is ByteBuf.
 * 3. To implement the DISCARD protocol, the handler has to ignore the received message. ByteBuf is a reference-counted object which has to be released explicitly via the release() method. Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler.
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/7
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        ((ByteBuf) msg).release(); // (3)



        /*
         *  1. This inefficient loop can actually be simplified to: System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII))
         *  2. Alternatively, you could do in.release() here.
         * */
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
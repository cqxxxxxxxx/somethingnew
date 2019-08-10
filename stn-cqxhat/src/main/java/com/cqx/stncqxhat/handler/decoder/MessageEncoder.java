package com.cqx.stncqxhat.handler.decoder;

import com.cqx.stncqxhat.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Message对象转换成String对象
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
@ChannelHandler.Sharable
public class MessageEncoder extends MessageToMessageEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        out.add(msg.getMsg() + "\n");
    }
}

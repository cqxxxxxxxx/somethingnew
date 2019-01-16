package com.cqx.stncqxhat.handler.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import static com.cqx.stncqxhat.constant.ServerConst.DELIMITER;

/**
 * 依据分隔符进行消息解码
 * 模仿着 {@link io.netty.handler.codec.LineBasedFrameDecoder} 和 {@link io.netty.handler.codec.DelimiterBasedFrameDecoder}
 * 写的，简化版本。辣鸡版本
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class DelimiterDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }


    private Object decode(ChannelHandlerContext ctx, ByteBuf buffer) {
        int i = findIndexOfDelimiter(buffer);
        if (i > 0) {
            ByteBuf frame;
            int length = i - buffer.readerIndex();
            int delimiterLength = DELIMITER.length;
            frame = buffer.readBytes(length + delimiterLength);
            return frame;
        }
        return null;
    }

    /**
     * Returns the index in the buffer of the delimiter.
     * Returns -1 if no end of line was found in the buffer.
     */
    private static int findIndexOfDelimiter(final ByteBuf buffer) {
        final int n = buffer.writerIndex();
        for (int i = buffer.readerIndex(); i < n - 1; i++) {
            final byte b = buffer.getByte(i);
            final byte b1 = buffer.getByte(i + 1);
            if (b == DELIMITER[0] && b1 == DELIMITER[1]) {
                return i;
            }
        }
        return -1;  // Not found.
    }
}

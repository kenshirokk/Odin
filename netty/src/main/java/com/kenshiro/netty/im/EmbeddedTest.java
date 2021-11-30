package com.kenshiro.netty.im;

import com.kenshiro.netty.im.message.LoginRequestMessage;
import com.kenshiro.netty.im.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EmbeddedTest {

    public static void main(String[] args) throws Exception {

        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(LogLevel.DEBUG),
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG),
                new MessageCodec());

        //encode
        LoginRequestMessage lm = new LoginRequestMessage("zhangsan", "12345", "张三");
        channel.writeOutbound(lm);

        //decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, lm, buf);
//        channel.writeInbound(buf);

        ByteBuf b1 = buf.slice(0, 100);
        ByteBuf b2 = buf.slice(100, buf.readableBytes() - 100);
        buf.retain();
        channel.writeInbound(b1);
        channel.writeInbound(b2);
    }
}

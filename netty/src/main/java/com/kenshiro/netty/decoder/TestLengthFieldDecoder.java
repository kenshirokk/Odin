package com.kenshiro.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TestLengthFieldDecoder {

    public static void main(String[] args) {

        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 5),
                new LoggingHandler(LogLevel.DEBUG)
        );


        //4个字节的长度,  实际内容
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        send(buf, "Hello, world");
        send(buf, "去你妈逼的傻逼");
        channel.writeInbound(buf);
    }

    private static void send(ByteBuf buf, String content) {
        byte[] bytes = content.getBytes();//实际内容
        int length = bytes.length; //实际内容长度
        buf.writeInt(length);
        buf.writeByte(1);
        buf.writeBytes(bytes);
    }


}

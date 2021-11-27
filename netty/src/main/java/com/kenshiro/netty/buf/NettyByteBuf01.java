package com.kenshiro.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);
        System.out.println(buf.capacity());
        for (int i = 0; i < 5; i++) {
            buf.writeByte(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(buf.readByte());
        }
    }
}

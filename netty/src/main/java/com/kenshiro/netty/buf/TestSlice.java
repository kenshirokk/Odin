package com.kenshiro.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.kenshiro.netty.buf.TestByteBuf.log;

public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'j', 'h', 'i', 'j', 'k', 'l', 'm', 'n'});
        log(buffer);
        ByteBuf b1 = buffer.slice(0, 5);
        b1.retain();
        log(b1);
        ByteBuf b2 = buffer.slice(5, 5);
        b2.retain();
        log(b2);


//        buffer.release();
        buffer.release();
        buffer.release();
        log(b1);
        log(b2);
    }
}

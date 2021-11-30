package com.kenshiro.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static com.kenshiro.netty.buf.TestByteBuf.log;

public class TestComposite {

    public static void main(String[] args) {
        ByteBuf b1 = ByteBufAllocator.DEFAULT.buffer();
        b1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf b2 = ByteBufAllocator.DEFAULT.buffer();
        b2.writeBytes(new byte[]{6,7,8,9,10});

        CompositeByteBuf buff = ByteBufAllocator.DEFAULT.compositeBuffer();
        buff.addComponents(true, b1, b2);
        buff.retain(3);
        b1.release();
        b2.release();
        log(buff);
    }
}

package com.kenshiro.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

import static io.netty.util.internal.StringUtil.NEWLINE;

public class TestByteBuf {

    public static void main(String[] args) {
        TestByteBuf tbb = new TestByteBuf();
        tbb.test2();
    }

    public void test1() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer.getClass());
        log(buffer);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            sb.append("ab");
        }

        buffer.writeBytes(sb.toString().getBytes());
        System.out.println(sb.toString().getBytes().length);
        log(buffer);
        String s = "ab";
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }

        byte[] b={(byte)0xe6,(byte)0x88,(byte)0x91};
        String str= new String (b);
        System.out.println(str);
    }


    public void test2() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        buf.writeInt(5);
        buf.writeInt(6);
        log(buf);
        for (int i = 0; i < 3; i++) {
            System.out.println(buf.readInt());

        }
    }

    public static void log(ByteBuf buf) {
        int length = buf.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder sb = new StringBuilder(rows * 80 * 2);
        sb.append("read index:").append(buf.readerIndex());
        sb.append(" write index:").append(buf.writerIndex());
        sb.append(" capacity:").append(buf.capacity());
        sb.append(NEWLINE);
        ByteBufUtil.appendPrettyHexDump(sb, buf);
        System.out.println(sb);
    }
}

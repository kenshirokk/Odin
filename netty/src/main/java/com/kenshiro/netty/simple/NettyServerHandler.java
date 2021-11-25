package com.kenshiro.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /*
    读取客户端消息
    ChannelHandlerContext ctx 上下文对象 含有 管道pipeline, 通道channel, 地址
    Object msg 客户端发送的数据 默认是Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(LocalDateTime.now() + " begin" + Thread.currentThread().getName());
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(3000);
                System.out.println(LocalDateTime.now() + "  three" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 10, TimeUnit.SECONDS);
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(LocalDateTime.now() + "  one" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(LocalDateTime.now() + "  two" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


//        System.out.println("server ctx = " + ctx);
//        System.out.println(ctx.channel());
//        System.out.println(ctx.pipeline());
//        //将msg转成ByteBuf
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("client send message is " + buf.toString(CharsetUtil.UTF_8));
//        System.out.println("client address is " + ctx.channel().remoteAddress());
    }

    /*
    读取数据完毕
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //一般 对发送数据编码
        ByteBuf buf = Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    /*
    处理异常, 一般就是关闭通道
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

package com.kenshiro.netty.dynamic;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /*
    当通道有读取事件时 触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.pipeline().addBefore(ctx.name(), "stringencoder", new StringEncoder(StandardCharsets.UTF_8));
        ctx.writeAndFlush("helloworld");
//        ctx.pipeline().addBefore(ctx.name(), "stringendoder", new StringEncoder(StandardCharsets.UTF_8));
    }
}

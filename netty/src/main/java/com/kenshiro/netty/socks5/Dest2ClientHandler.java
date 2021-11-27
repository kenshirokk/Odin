package com.kenshiro.netty.socks5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Dest2ClientHandler extends ChannelInboundHandlerAdapter {

    private final ChannelHandlerContext context;

    public Dest2ClientHandler(ChannelHandlerContext context) {
        this.context = context;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        context.writeAndFlush(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        context.channel().close();
    }
}

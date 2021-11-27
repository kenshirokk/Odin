package com.kenshiro.netty.socks5;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Client2DestHandler extends ChannelInboundHandlerAdapter {

    private final ChannelFuture cf;

    public Client2DestHandler(ChannelFuture cf) {
        this.cf = cf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        cf.channel().writeAndFlush(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        cf.channel().close();
    }
}

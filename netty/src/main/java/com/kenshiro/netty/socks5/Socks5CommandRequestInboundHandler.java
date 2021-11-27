package com.kenshiro.netty.socks5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socksx.v5.*;

public class Socks5CommandRequestInboundHandler extends SimpleChannelInboundHandler<DefaultSocks5CommandRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DefaultSocks5CommandRequest msg) throws Exception {


        Bootstrap b = new Bootstrap();
        b.group(ctx.channel().eventLoop())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Dest2ClientHandler(ctx));
                    }
                });
        ChannelFuture cf = b.connect(msg.dstAddr(), msg.dstPort());
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    ctx.pipeline().addLast(new Client2DestHandler(future));
                    Socks5CommandResponse response = new DefaultSocks5CommandResponse(Socks5CommandStatus.SUCCESS,
                            Socks5AddressType.IPv4);
                    ctx.writeAndFlush(response);
                } else {
                    DefaultSocks5CommandResponse response =
                            new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE, Socks5AddressType.IPv4);
                    ctx.writeAndFlush(response);
                }
            }
        });

    }
}

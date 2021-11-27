package com.kenshiro.netty.socks;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socksx.SocksMessage;
import io.netty.handler.codec.socksx.v5.DefaultSocks5CommandResponse;
import io.netty.handler.codec.socksx.v5.Socks5CommandRequest;
import io.netty.handler.codec.socksx.v5.Socks5CommandStatus;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.Promise;

@ChannelHandler.Sharable
public class SocksServerConnectHandler extends SimpleChannelInboundHandler<SocksMessage> {

    private final Bootstrap b = new Bootstrap();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocksMessage msg) throws Exception {
        if (msg instanceof Socks5CommandRequest) {
            Socks5CommandRequest request = (Socks5CommandRequest) msg;
            Promise<Channel> promise = ctx.executor().newPromise();
            promise.addListener(new FutureListener<Channel>() {

                @Override
                public void operationComplete(Future<Channel> future) throws Exception {
                    Channel outboundChannel = future.getNow();
                    if (future.isSuccess()) {
                        ChannelFuture responseFuture = ctx.channel().write(new DefaultSocks5CommandResponse(
                                Socks5CommandStatus.SUCCESS,
                                request.dstAddrType(),
                                request.dstAddr(),
                                request.dstPort()));
                        responseFuture.addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture future) throws Exception {
                                ctx.pipeline().remove(SocksServerConnectHandler.this);
                                outboundChannel.pipeline().addLast(new RelayHandler(ctx.channel()));
                                ctx.pipeline().addLast(new RelayHandler(outboundChannel));
                            }
                        });
                    } else {
                        ctx.channel().writeAndFlush(new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE,
                                request.dstAddrType()));
                        SocksServerUtils.closeOnFlush(ctx.channel());
                    }
                }
            });

            Channel inboundChannel = ctx.channel();
            b.group(inboundChannel.eventLoop())
                    .channel(NioSocketChannel.class)
                    .handler(new DirectClientHandler(promise));

            b.connect(request.dstAddr(), request.dstPort()).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {

                    } else {
                        ctx.channel().writeAndFlush(new DefaultSocks5CommandResponse(Socks5CommandStatus.FAILURE,
                                request.dstAddrType()));
                        SocksServerUtils.closeOnFlush(ctx.channel());
                    }
                }
            });
        } else {
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SocksServerUtils.closeOnFlush(ctx.channel());
    }
}

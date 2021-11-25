package com.kenshiro.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("为什么为什么");
        ch.pipeline().addLast("MyHttpcodec", new HttpServerCodec());
        ch.pipeline().addLast("MyHttpHandler", new HttpServerHandler());
    }
}

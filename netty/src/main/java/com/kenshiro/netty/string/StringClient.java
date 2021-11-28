package com.kenshiro.netty.string;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        ChannelFuture cf = b.connect("127.0.0.1", 8080);
//        cf.sync();
        Channel channel = cf.channel();
        log.debug("============> {}", channel);
        channel.writeAndFlush("helloworld");

    }
}

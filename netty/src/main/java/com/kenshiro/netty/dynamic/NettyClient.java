package com.kenshiro.netty.dynamic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler log = new LoggingHandler(LogLevel.DEBUG);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(log);
//                            pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));
                            pipeline.addLast(new NettyClientHandler());
                            pipeline.addLast(log);
                        }
                    });

            //启动客户端去连接服务器
            ChannelFuture cf = b.connect("127.0.0.1", 8080).sync();
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}

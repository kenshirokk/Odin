package com.kenshiro.netty.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(loggingHandler);
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    StudentPOJO.Student stu =
                                            StudentPOJO.Student.newBuilder().setId(100).setName("babaisme").build();
                                    ctx.channel().writeAndFlush(stu);
                                    ctx.channel().close();
                                }
                            });
                            pipeline.addLast(new ProtobufEncoder());
                        }
                    });
            ChannelFuture cf = b.connect("127.0.0.1", 8080).sync();
            cf.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }

    }
}

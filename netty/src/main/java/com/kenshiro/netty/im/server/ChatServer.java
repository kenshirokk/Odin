package com.kenshiro.netty.im.server;

import com.kenshiro.netty.im.message.LoginRequestMessage;
import com.kenshiro.netty.im.message.LoginResponseMessage;
import com.kenshiro.netty.im.protocol.MessageCodecSharable;
import com.kenshiro.netty.im.protocol.ProtocolFrameDecoder;
import com.kenshiro.netty.im.server.service.UserService;
import com.kenshiro.netty.im.server.service.UserServiceFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ChatServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProtocolFrameDecoder());
                            ch.pipeline().addLast(loggingHandler);
                            ch.pipeline().addLast(messageCodecSharable);
                            ch.pipeline().addLast(new SimpleChannelInboundHandler<LoginRequestMessage>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
                                    String username = msg.getUsername();
                                    String password = msg.getPassword();
                                    UserService userService = UserServiceFactory.getUserService();
                                    boolean login = userService.login(username, password);
                                    LoginResponseMessage message;
                                    if (login) {
                                        message = new LoginResponseMessage(true, "登录成功");
                                    } else {
                                        message = new LoginResponseMessage(false, "用户名密码错误");
                                    }

                                    ctx.writeAndFlush(message);
                                }
                            });
                        }
                    });
            ChannelFuture cf = b.bind(8080).sync();
            cf.channel().closeFuture().sync();

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

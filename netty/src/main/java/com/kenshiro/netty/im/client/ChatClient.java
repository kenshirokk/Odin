package com.kenshiro.netty.im.client;

import com.kenshiro.netty.im.message.LoginRequestMessage;
import com.kenshiro.netty.im.message.LoginResponseMessage;
import com.kenshiro.netty.im.protocol.MessageCodecSharable;
import com.kenshiro.netty.im.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ChatClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler(LogLevel.ERROR);
        MessageCodecSharable messageCodecSharable = new MessageCodecSharable();
        CountDownLatch WAIT = new CountDownLatch(1);
        AtomicBoolean LOGIN = new AtomicBoolean(false);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtocolFrameDecoder());
                            pipeline.addLast(loggingHandler);
                            pipeline.addLast(messageCodecSharable);
                            pipeline.addLast("client handler", new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    new Thread(() -> {
                                        Scanner in = new Scanner(System.in);
                                        System.out.println("请输入用户名");
                                        String username = in.nextLine();
                                        System.out.println("请输入密码");
                                        String password = in.nextLine();

                                        LoginRequestMessage message = new LoginRequestMessage(username, password);
                                        ctx.writeAndFlush(message);

                                        System.out.println("等待");
                                        try {
                                            WAIT.await();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        if (!LOGIN.get()) {
                                            ctx.channel().close();
                                            return;
                                        }
                                        for (; ; ) {
                                            System.out.println("=================================");
                                            System.out.println("send [username] [content]");
                                            System.out.println("gsend [group name] [content]");
                                            System.out.println("gcreate [group name] [m1, m2, m3...]");
                                            System.out.println("gmembers [group name]");
                                            System.out.println("gjoin [group name]");
                                            System.out.println("gquit [group name]");
                                            System.out.println("quit");
                                            System.out.println("=================================");
                                            String command = in.nextLine();
                                            String[] s = command.split(" ");
                                            switch (s[0]) {
                                                case "send":

                                                    break;
                                                case "gsend":
                                                    break;
                                                case "gcreate":
                                                    break;
                                                case "gmembers":
                                                    break;
                                                case "gjoin":
                                                    break;
                                                case "gquit":
                                                    break;
                                                case "quit":
                                                    break;
                                            }
                                        }
                                    }, "system in").start();
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.debug("msg : {}", msg);
                                    if (msg instanceof LoginResponseMessage) {
                                        LoginResponseMessage response = (LoginResponseMessage) msg;
                                        if (response.isSuccess()) {
                                            //如果登录成功
                                            LOGIN.set(true);
                                        }
                                        WAIT.countDown();
                                    }
                                }
                            });
                        }
                    });
            ChannelFuture cf = b.connect("127.0.0.1", 8080).sync();
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}

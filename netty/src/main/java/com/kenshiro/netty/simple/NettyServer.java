package com.kenshiro.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建2个线程组
        //boss只是处理连接请求, 真正与客户端业务处理的会交给worker
        //2个都是无限循环
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        //创建服务器端启动的对象, 配置启动参数
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)       //设置2个线程组
                    .channel(NioServerSocketChannel.class)//使用这个作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)  //设置现成队列得到连接个数 用来初始化服务端可连接队列
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持连接活动状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象 匿名
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("server is ready...");
            //绑定一个端口, 并且同步, 生成一个ChannelFuture对象
            ChannelFuture cf = b.bind(8888).sync();
            //对关闭通道监听
            cf.channel().closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

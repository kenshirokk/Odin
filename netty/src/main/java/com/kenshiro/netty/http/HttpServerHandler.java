package com.kenshiro.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg是不是httprequest请求

        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型: " + msg.getClass());
            System.out.println("客户端地址: " + ctx.channel().remoteAddress());

            //回复信息给浏览器 [http协议]
            ByteBuf buf = Unpooled.copiedBuffer("hello  我是服务器", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, buf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());

            ctx.writeAndFlush(response);
        }
    }

}

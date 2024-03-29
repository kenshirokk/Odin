/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.kenshiro.netty.oldsocks5;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.socks.*;


@ChannelHandler.Sharable
public final class SocksServerHandler extends SimpleChannelInboundHandler<SocksRequest> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, SocksRequest socksRequest) throws Exception {
        switch (socksRequest.requestType()) {
            case INIT: {
                System.out.println("===========================INIT======="+ctx.channel());
                // auth support example
                //ctx.pipeline().addFirst(new SocksAuthRequestDecoder());
                //ctx.write(new SocksInitResponse(SocksAuthScheme.AUTH_PASSWORD));
                ctx.pipeline().addFirst(new SocksCmdRequestDecoder());
                ctx.write(new SocksInitResponse(SocksAuthScheme.NO_AUTH));
                break;
            }
            case AUTH:
                System.out.println("===========================AUTH======="+ctx.channel());

                ctx.pipeline().addFirst(new SocksCmdRequestDecoder());
                ctx.write(new SocksAuthResponse(SocksAuthStatus.SUCCESS));
                break;
            case CMD:
                System.out.println("===========================CMD======="+ctx.channel());

                SocksCmdRequest req = (SocksCmdRequest) socksRequest;
                if (req.cmdType() == SocksCmdType.CONNECT) {
                    ctx.pipeline().addLast(new SocksServerConnectHandler());
                    ctx.pipeline().remove(this);
                    ctx.fireChannelRead(socksRequest);
                } else {
                    ctx.close();
                }
                break;
            case UNKNOWN:
                ctx.close();
                break;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        throwable.printStackTrace();
        SocksServerUtils.closeOnFlush(ctx.channel());
    }
}

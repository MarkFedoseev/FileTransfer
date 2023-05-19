package org.example.P2P.Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

public class HolePunchingHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext client1;
    private ChannelHandlerContext client2;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        if (client1 == null) {
            client1 = ctx;
        } else if (client2 == null) {
            client2 = ctx;
            InetSocketAddress addr1 = (InetSocketAddress) client1.channel().remoteAddress();
            InetSocketAddress addr2 = (InetSocketAddress) client2.channel().remoteAddress();
            client1.writeAndFlush(addr2);  // You need to implement this
            client2.writeAndFlush(addr1);  // You need to implement this
        } else {
            System.out.print("more than two clients");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (ctx == client1) {
            client1 = null;
        } else if (ctx == client2) {
            client2 = null;
        }
    }
}
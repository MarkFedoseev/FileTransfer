package org.example.P2P.Client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;

public class PeerHandler extends SimpleChannelInboundHandler<InetSocketAddress> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, InetSocketAddress endpoint) {
        // Received public endpoint of the other peer from the server
        // Start sending packets to this endpoint
        // You need to implement this
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
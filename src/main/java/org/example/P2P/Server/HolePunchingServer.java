package org.example.P2P.Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class HolePunchingServer {

    public static void main(String address, int port) throws Exception {
        new HolePunchingServer().start(address, port);
    }

    private static EventLoopGroup servGroup = new NioEventLoopGroup();
    private static EventLoopGroup clientGroup = new NioEventLoopGroup();
    private void start(String address, int port) throws Exception {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(servGroup, clientGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new HolePunchingHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            Channel ch = b.bind(new InetSocketAddress(address, port)).sync().channel();
            ch.closeFuture().sync();
        } finally {
            servGroup.shutdownGracefully();
            clientGroup.shutdownGracefully();
        }
    }
    public static void stop(){
        servGroup.shutdownGracefully();
        clientGroup.shutdownGracefully();
    }
}
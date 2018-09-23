package com.guanhang.nettyguide.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {
    public void bind(int port) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //LoggingHandler会打印netty的日志
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new ChildChannelHandler());
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("Server started!");
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //解决粘包问题
            /*相关参数的解释：
            maxFrameLength： 结构的最大长度
            lengthFieldOffset：长度属性的位置
            lengthFieldLength：长度属性的长度
            lengthAdjustment：长度属性的补偿(填充)值
            initialBytesToStrip：解码时需要提取的字节数*/
            socketChannel.pipeline().addLast("frameDecoder",
                    new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
            socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
            //在ByteBuf消息之前增加2个字节的消息长度字段
            socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
            socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
            socketChannel.pipeline().addLast(new EchoServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("use  default port");
            }
        }
        new EchoServer().bind(port);
    }
}

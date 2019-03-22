package im.server;

import im.server.pipeline.NettyServerPipeline;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/21 21:41
 * @Description:
 */
public class NettyServer {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class).childHandler(new NettyServerPipeline());


            bind(b, PORT);
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private static void bind(ServerBootstrap b, int port) throws Exception{
        ChannelFuture channelFuture = b.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
                } else {
                    System.out.println(new Date() + ": 端口[" + port + "]绑定失败!");
                }
            }
        }).sync();

        channelFuture.channel().closeFuture().sync();
    }
}
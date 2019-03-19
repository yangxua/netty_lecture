package quick.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import quick.netty.pkg.AuthHandler;
import quick.netty.pkg.Spliter;
import quick.netty.pkg.codec.PacketDecoder;
import quick.netty.pkg.codec.PacketEncoder;
import quick.netty.pkg.ser_handler.*;

/**
 * @Auther: allanyang
 * @Date: 2019/3/5 15:10
 * @Description:
 */
public class Server {


    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginRequestHandler());
                ch.pipeline().addLast(new AuthHandler());
                ch.pipeline().addLast(new MessageRequestHandler());
                ch.pipeline().addLast(new CreateGroupRequestHandler());
                ch.pipeline().addLast(new LogoutRequestHandler());
                ch.pipeline().addLast(new JoinGroupRequestHandler());
                ch.pipeline().addLast(new QuitGroupRequestHandler());
                ch.pipeline().addLast(new ListGroupMemberRequestHandler());
                ch.pipeline().addLast(new PacketEncoder());
            }
        });

        bind(b, 8888);
    }

    private static void bind(final ServerBootstrap b,final int i) {
        b.bind(i).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success");
                    System.out.println(i);
                } else {
                    System.out.println("bind [" + i + "] error");
                    bind(b, i+1);
                }
            }
        });
    }


}
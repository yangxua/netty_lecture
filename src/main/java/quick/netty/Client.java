package quick.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import quick.netty.pkg.Spliter;
import quick.netty.pkg.cli_handler.CreateGroupResponseHandler;
import quick.netty.pkg.cli_handler.LoginResponseHandler;
import quick.netty.pkg.cli_handler.MessageResponseHandler;
import quick.netty.pkg.cli_handler.console.ConsoleCommandMgr;
import quick.netty.pkg.cli_handler.console.LoginConsoleCommand;
import quick.netty.pkg.cli_handler.LogoutResponseHandler;
import quick.netty.pkg.codec.PacketDecoder;
import quick.netty.pkg.codec.PacketEncoder;
import quick.netty.pkg.session.SessionUtils;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/3/7 17:53
 * @Description:
 */
public class Client {

    public static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(workGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginResponseHandler());
                ch.pipeline().addLast(new LogoutResponseHandler());
                ch.pipeline().addLast(new MessageResponseHandler());
                ch.pipeline().addLast(new CreateGroupResponseHandler());
                ch.pipeline().addLast(new PacketEncoder());
            }

        });

        connect(b,"127.0.0.1",8888,MAX_RETRY);
    }

    private static void connect(Bootstrap b, String ip, int port, int retry) {
        b.connect(ip,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {

                    Channel channel = ((ChannelFuture) future).channel();
                    asynConsoleProcessor(channel);

                    System.out.println("success connected");
                } else if (retry == 0) {
                    System.err.println("次数已用完");
                } else {
                        System.out.println("failed connected");

                        int order = (MAX_RETRY - retry) + 1;
                        int delay = 1 << order;

                        b.config().group().schedule(() -> connect(b, ip, port, retry - 1), delay, TimeUnit
                                .SECONDS);
                    }
                }

        });

    }

    private static void asynConsoleProcessor(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandMgr consoleCommandMgr = new ConsoleCommandMgr();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    loginConsoleCommand.exec(sc, channel);
                } else {
                    consoleCommandMgr.exec(sc, channel);
                }
            }
        }).start();
    }
}
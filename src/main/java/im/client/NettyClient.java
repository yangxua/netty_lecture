package im.client;

import im.client.concole.impl.LoginConsoleCommand;
import im.client.concole.mgr.ConsoleCommandMgr;
import im.client.pipeline.NettyClientPipeline;
import im.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/3/21 21:40
 * @Description:
 */
public class NettyClient {

    private static final int PORT = 8888;
    private static final String IP = "127.0.0.1";
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup).channel(NioSocketChannel.class).handler(new NettyClientPipeline());

            connect(b, IP, PORT, MAX_RETRY);
        } catch (Exception e) {
            workGroup.shutdownGracefully();
        }
    }

    private static void connect(Bootstrap b, String ip, int port, int retry)  {
        try {
            ChannelFuture channelFuture = b.connect(ip, port).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                        Channel channel = ((ChannelFuture) future).channel();
                        asynConsoleThread(channel);
                    } else if (retry == 0) {
                        System.err.println("重试次数已用完，放弃连接！");
                    } else {
                        // 第几次重连
                        int order = (MAX_RETRY - retry) + 1;
                        // 本次重连的间隔
                        int delay = 1 << order;
                        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                        b.config().group().schedule(() -> connect(b, ip, port, retry - 1), delay, TimeUnit
                                .SECONDS);
                    }
                }
            }).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void asynConsoleThread(Channel channel) {
        ConsoleCommandMgr consoleCommandMgr = new ConsoleCommandMgr();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    if (!SessionUtil.hasLogin(channel)) {
                        loginConsoleCommand.exec(scanner, channel);
                    } else {
                        consoleCommandMgr.exec(scanner, channel);
                    }
                }
            }
        }).start();
    }
}
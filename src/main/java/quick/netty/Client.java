package quick.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import newland.rpc.model.MessageRequest;
import quick.netty.pkg.LoginUtils;
import quick.netty.pkg.MessageRequestPackage;
import quick.netty.pkg.PacketCodeC;

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
        b.group(workGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new FirstClientHandler());
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
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.next();

                    MessageRequestPackage request = new MessageRequestPackage();
                    request.setMessage(line);
                    ByteBuf buf = PacketCodeC.getInstance().encode(channel.alloc(), request);

                    channel.writeAndFlush(buf);
                }
            }
        }).start();
    }
}
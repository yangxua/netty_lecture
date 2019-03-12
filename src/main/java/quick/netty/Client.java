package quick.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

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

    private static void connect(Bootstrap b, String ip, int port,int retry) {
        b.connect(ip,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("success connected");
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
}
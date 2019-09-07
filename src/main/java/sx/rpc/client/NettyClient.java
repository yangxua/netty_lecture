package sx.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import sx.rpc.NettyConstant;
import sx.rpc.codec.NettyMessageDecoder;
import sx.rpc.codec.NettyMessageEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 16:58
 * @Description:
 */
@Slf4j
public class NettyClient {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }

    private void connect(int port, String host) throws InterruptedException {
        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(bossGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            pipeline.addLast("MessageEncoder", new NettyMessageEncoder());
                            pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            pipeline.addLast("LoginAuthHandler",
                                    new LoginAuthReqHandler());
                            pipeline.addLast("HeartBeatHandler",
                                    new HeartBeatReqHandler());
                        }
                    });
            ChannelFuture channelFuture = b.connect(new InetSocketAddress(host, port), new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
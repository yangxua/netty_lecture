package sx.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import sx.rpc.NettyConstant;
import sx.rpc.codec.NettyMessageDecoder;
import sx.rpc.codec.NettyMessageEncoder;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 16:14
 * @Description:
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }

    private void bind() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        pipeline.addLast(new NettyMessageEncoder());
                        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        pipeline.addLast(new LoginAuthRespHandler());
                        pipeline.addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });

        b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
        log.info("Netty server start ok : "
                + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));
    }
}
package im2.server;

import im2.codec.PacketCodecHandler;
import im2.codec.Spliter;
import im2.server.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/5/15 17:46
 * @Description:
 */
public class NettyServerPipeline extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new IMIdleStateHandler());
        pipeline.addLast(new Spliter());
        pipeline.addLast(PacketCodecHandler.INSTANCE);
        pipeline.addLast(LoginRequestHandler.INSTANCE);
        pipeline.addLast(AuthHandler.INSTANCE);
        pipeline.addLast(IMHandler.INSTANCE);
    }
}

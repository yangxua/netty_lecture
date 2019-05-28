package im2.client;

import im2.client.handler.CreateGroupResponseHandler;
import im2.client.handler.LoginResponseHandler;
import im2.client.handler.MessageResponseHandler;
import im2.codec.PacketDecoder;
import im2.codec.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/5/15 18:07
 * @Description:
 */
public class NettyClientPipeline extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginResponseHandler());
        pipeline.addLast(new MessageResponseHandler());
        pipeline.addLast(new CreateGroupResponseHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
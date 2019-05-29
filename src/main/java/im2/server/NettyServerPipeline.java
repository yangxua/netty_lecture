package im2.server;

import im2.codec.PacketDecoder;
import im2.codec.PacketEncoder;
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

        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginRequestHandler());
        pipeline.addLast(new AuthHandler());
        pipeline.addLast(new MessageRequestHandler());
        pipeline.addLast(new CreateGroupRequestHandler());
        pipeline.addLast(new ListGroupMembersRequestHandler());
        pipeline.addLast(new JoinGroupRequestHandler());
        pipeline.addLast(new QuitGroupRequestHandler());
        pipeline.addLast(new PacketEncoder());
    }
}

package im.server.pipeline;

import im.codec.PacketCodecHandler;
import im.codec.Spliter;
import im.server.handler.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/3/21 21:53
 * @Description:
 */
public class NettyServerPipeline extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new Spliter());
        pipeline.addLast(PacketCodecHandler.INSTANCE);
        pipeline.addLast(LoginRequestHandler.INSTANCE);
        pipeline.addLast(SendToUserRequestHandler.INSTANCE);
        pipeline.addLast(CreateGroupRequestHandler.INSTANCE);
        pipeline.addLast(JoinGroupRequestHandler.INSTANCE);
        pipeline.addLast(QuitGroupRequestHandler.INSTANCE);
        pipeline.addLast(ListGroupMembersRequestHandler.INSTANCE);
        pipeline.addLast(SendToGroupRequestHandler.INSTANCE);
    }
}
package im.client.pipeline;

import im.client.handler.*;
import im.codec.PacketDecoder;
import im.codec.PacketEncoder;
import im.codec.Spliter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: allanyang
 * @Date: 2019/3/21 21:52
 * @Description:
 */
public class NettyClientPipeline extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new Spliter());
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new LoginResponseHandler());
        pipeline.addLast(new SendToUserResponseHandler());
        pipeline.addLast(new CreateGroupResponseHandler());
        pipeline.addLast(new JoinGroupResponseHandler());
        pipeline.addLast(new QuitGroupResponseHandler());
        pipeline.addLast(new ListGroupMembersResponseHandler());
        pipeline.addLast(new SendToGroupResponseHandler());
        pipeline.addLast(new PacketEncoder());
    }
}
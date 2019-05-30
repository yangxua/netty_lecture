package im2.server.handler;

import im2.protocol.request.JoinGroupRequestPacket;
import im2.protocol.response.JoinGroupResponsePacket;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:16
 * @Description:
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChnnelGroup(groupId);
        if (null != channelGroup) {
            channelGroup.add(ctx.channel());
        }

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
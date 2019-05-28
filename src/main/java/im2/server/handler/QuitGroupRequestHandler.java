package im2.server.handler;

import im2.protocol.request.QuitGroupRequestPacket;
import im2.protocol.response.QuitGroupResponsePacket;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:33
 * @Description:
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();

        ChannelGroup chnnelGroup = SessionUtil.getChnnelGroup(groupId);
        if (null != chnnelGroup) {
            chnnelGroup.remove(ctx.channel());
        }

        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
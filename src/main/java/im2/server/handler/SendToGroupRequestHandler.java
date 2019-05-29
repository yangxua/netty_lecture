package im2.server.handler;

import im2.protocol.request.SendToGroupRequestPacket;
import im2.protocol.response.SendToGroupResponsePacket;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: allanyang
 * @Date: 2019/5/29 21:09
 * @Description:
 */
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();
        String msg = packet.getMsg();

        ChannelGroup chnnelGroup = SessionUtil.getChnnelGroup(groupId);

        SendToGroupResponsePacket responsePacket = new SendToGroupResponsePacket();
        responsePacket.setMsg(msg);
        responsePacket.setUserName(SessionUtil.getSession(ctx.channel()).getUserName());

        chnnelGroup.writeAndFlush(responsePacket);
    }
}
package im.server.handler;

import im.protocol.request.SendToGroupRequestPacket;
import im.protocol.response.SendToGroupResponsePacket;
import im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 18:12
 * @Description:
 */
@ChannelHandler.Sharable
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    public static final SendToGroupRequestHandler INSTANCE = new SendToGroupRequestHandler();
    private SendToGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();
        String message = msg.getMessage();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        SendToGroupResponsePacket responsePacket = new SendToGroupResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUserName(SessionUtil.getSession(ctx.channel()).getUserName());
        responsePacket.setMsg(message);

        channelGroup.writeAndFlush(responsePacket);
    }
}
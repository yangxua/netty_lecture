package im.server.handler;

import com.google.common.collect.Lists;
import im.protocol.request.ListGroupMembersRequestPacket;
import im.protocol.response.ListGroupMembersResponsePacket;
import im.session.Session;
import im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 16:38
 * @Description:
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();
    private ListGroupMembersRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket msg) throws Exception {
        String groupId = msg.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> session = Lists.newArrayList();

        for (Channel channel : channelGroup) {
            session.add(SessionUtil.getSession(channel));
        }

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessions(session);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
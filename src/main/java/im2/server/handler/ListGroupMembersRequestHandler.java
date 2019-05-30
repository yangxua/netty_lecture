package im2.server.handler;

import com.google.common.collect.Lists;
import im2.protocol.request.ListGroupMembersRequestPacket;
import im2.protocol.response.ListGroupMembersResponsePacket;
import im2.session.Session;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:45
 * @Description:
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket packet) throws Exception {
        String groupId = packet.getGroupId();

        ChannelGroup chnnelGroup = SessionUtil.getChnnelGroup(groupId);

        List<Session> list = Lists.newArrayListWithCapacity(chnnelGroup.size());

        chnnelGroup.forEach(channel -> {
            Session session = SessionUtil.getSession(channel);
            if (null != session) {
                list.add(session);
            }
        });

        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setList(list);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
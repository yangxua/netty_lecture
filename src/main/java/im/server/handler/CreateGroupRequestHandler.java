package im.server.handler;

import com.google.common.collect.Lists;
import im.protocol.request.CreateGroupRequestPacket;
import im.protocol.response.CreateGroupResponsePacket;
import im.util.IDUtil;
import im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 21:49
 * @Description:
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();
    private CreateGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIds = msg.getUserIds();

        List<String> userNames = Lists.newArrayListWithExpectedSize(userIds.size());
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (String userId : userIds) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNames.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        String groupId = IDUtil.random();

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNames(userNames);

        channelGroup.writeAndFlush(responsePacket);

        System.out.print("群创建成功，id 为 " + responsePacket.getGroupId() + ", ");
        System.out.println("群里面有：" + responsePacket.getUserNames());

        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
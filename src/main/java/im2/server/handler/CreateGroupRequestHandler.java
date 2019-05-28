package im2.server.handler;

import com.google.common.collect.Lists;
import im2.protocol.request.CreateGroupRequestPacket;
import im2.protocol.response.CreateGroupResponsePacket;
import im2.util.RandomUtil;
import im2.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/5/27 21:45
 * @Description:
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    private static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) throws Exception {

        List<String> userIdList = packet.getUserIdList();
        List<String> userNameList = Lists.newArrayListWithCapacity(userIdList.size());

        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (null != channel) {
                GROUP.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(RandomUtil.random());
        responsePacket.setUserNameList(userNameList);

        GROUP.forEach(channel -> {
            channel.writeAndFlush(responsePacket);
        });

        System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + responsePacket.getUserNameList());
    }
}
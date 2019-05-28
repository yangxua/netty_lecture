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


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket packet) throws Exception {

        List<String> userIdList = packet.getUserIdList();
        List<String> userNameList = Lists.newArrayListWithCapacity(userIdList.size());

        ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        
        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (null != channel) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });


        String groupId = RandomUtil.random();
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        responsePacket.setUserNameList(userNameList);

        channelGroup.forEach(channel -> {
            channel.writeAndFlush(responsePacket);
        });

        System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + responsePacket.getUserNameList());

        SessionUtil.bindChannelGroup(groupId, channelGroup);
    }
}
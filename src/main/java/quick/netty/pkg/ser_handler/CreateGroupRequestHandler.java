package quick.netty.pkg.ser_handler;

import com.google.common.collect.Lists;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import quick.netty.pkg.CreateGroupRequestPackage;
import quick.netty.pkg.CreateGroupResponsePackage;
import quick.netty.pkg.session.SessionUtils;
import quick.netty.pkg.utils.IdUtils;

import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 19:46
 * @Description:
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPackage> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPackage msg) throws Exception {
        List<String> userIds = msg.getUserIds();

        List<String> userNames = Lists.newArrayListWithExpectedSize(userIds.size());
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for(String userId :userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                userNames.add(SessionUtils.getSession(channel).getUserName());
                channelGroup.add(channel);
            }
        }

        String groupId = IdUtils.random();
        SessionUtils.bindChannelGroup(groupId, channelGroup);

        CreateGroupResponsePackage responsePackage = new CreateGroupResponsePackage();
        responsePackage.setSuccess(true);
        responsePackage.setGroupId(groupId);
        responsePackage.setUserNames(userNames);

        channelGroup.writeAndFlush(responsePackage);

        System.out.print("群创建成功，id 为[" + responsePackage.getGroupId() + "], ");
        System.out.println("群里面有：" + responsePackage.getUserNames());
    }
}
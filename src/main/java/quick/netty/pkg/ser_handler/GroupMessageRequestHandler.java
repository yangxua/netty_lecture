package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import quick.netty.pkg.GroupMessageRequestPackage;
import quick.netty.pkg.GroupMessageResponsePackage;
import quick.netty.pkg.session.SessionUtils;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 18:13
 * @Description:
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPackage> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
    private GroupMessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPackage msg) throws Exception {
        String groupId = msg.getGroupId();

        GroupMessageResponsePackage responsePackage = new GroupMessageResponsePackage();
        responsePackage.setGroupId(groupId);
        responsePackage.setMessage(msg.getMessage());
        responsePackage.setUserName(SessionUtils.getSession(ctx.channel()).getUserName());

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePackage);
    }
}
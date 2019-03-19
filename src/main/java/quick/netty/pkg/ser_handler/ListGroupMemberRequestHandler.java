package quick.netty.pkg.ser_handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import quick.netty.pkg.ListGroupMemberRequestPackage;
import quick.netty.pkg.ListGroupMemberResposnePackage;
import quick.netty.pkg.session.SessionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:49
 * @Description:
 */
@ChannelHandler.Sharable
public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPackage> {

    public static final ListGroupMemberRequestHandler INSTANCE = new ListGroupMemberRequestHandler();
    private ListGroupMemberRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPackage msg) throws Exception {
        String groupId = msg.getGroupId();

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);

        List<String> userNames = new ArrayList<>();

        for (Channel channel : channelGroup) {
            userNames.add(SessionUtils.getSession(channel).getUserName());
        }

        ListGroupMemberResposnePackage responsePackage = new ListGroupMemberResposnePackage();
        responsePackage.setGroupId(groupId);
        responsePackage.setSuccess(true);
        responsePackage.setUserNames(userNames);

        ctx.channel().writeAndFlush(responsePackage);
    }
}
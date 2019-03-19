package quick.netty.pkg.ser_handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import quick.netty.pkg.QuitGroupRequestPackage;
import quick.netty.pkg.QuitGroupResponsePackage;
import quick.netty.pkg.session.SessionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:40
 * @Description:
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPackage msg) throws Exception {
        String groupId = msg.getGroupId();

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        List<String> userName = new ArrayList<>();

        for (Channel c : channelGroup) {
            userName.add(SessionUtils.getSession(c).getUserName());
        }

        QuitGroupResponsePackage responsePackage = new QuitGroupResponsePackage();
        responsePackage.setGroupId(groupId);
        responsePackage.setUserNames(userName);
        responsePackage.setSuccess(true);

        ctx.channel().writeAndFlush(responsePackage);
    }
}
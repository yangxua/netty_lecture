package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import quick.netty.pkg.JoinGroupRequestPackage;
import quick.netty.pkg.JoinGroupResponsePackage;
import quick.netty.pkg.session.SessionUtils;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:26
 * @Description:
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPackage> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();
    private JoinGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPackage msg) throws Exception {
        String groupId = msg.getGroupId();

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePackage responsePackage = new JoinGroupResponsePackage();
        responsePackage.setSuccess(true);
        responsePackage.setGroupId(groupId);

        channelGroup.writeAndFlush(responsePackage);
    }
}
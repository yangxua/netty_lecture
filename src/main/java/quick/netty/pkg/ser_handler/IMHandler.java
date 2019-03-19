package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.Command;
import quick.netty.pkg.Package;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 19:45
 * @Description:
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Package> {

    public static final IMHandler INSTANCE = new IMHandler();
    private IMHandler(){}

    private static final Map<Byte, SimpleChannelInboundHandler<? extends Package>> selector = new HashMap<Byte, SimpleChannelInboundHandler<? extends Package>>() {{
        put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        put(Command.LIST_GROUP_MEMBER_REQUSET, ListGroupMemberRequestHandler.INSTANCE);
        put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        put(Command.LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }};


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Package msg) throws Exception {
        selector.get(msg.getCommand()).channelRead(ctx, msg);
    }
}
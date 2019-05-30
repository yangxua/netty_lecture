package im2.server.handler;

import im2.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static im2.protocol.command.Command.*;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 10:49
 * @Description:
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> command2Handler;

    private IMHandler() {
        command2Handler = new HashMap<>();

        command2Handler.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        command2Handler.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        command2Handler.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        command2Handler.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        command2Handler.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        command2Handler.put(SEND_TO_USER_GROUP_REQUEST, SendToGroupRequestHandler.INSTANCE);
        command2Handler.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        command2Handler.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
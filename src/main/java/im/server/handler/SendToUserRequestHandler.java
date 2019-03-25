package im.server.handler;

import im.protocol.request.SendToUserRequestPacket;
import im.protocol.response.SendToUserResponsePakcet;
import im.session.Session;
import im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 11:50
 * @Description:
 */
@ChannelHandler.Sharable
public class SendToUserRequestHandler extends SimpleChannelInboundHandler<SendToUserRequestPacket> {

    public static final SendToUserRequestHandler INSTANCE = new SendToUserRequestHandler();
    private SendToUserRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToUserRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息:" + msg.getMessage());

        Session session = SessionUtil.getSession(ctx.channel());

        SendToUserResponsePakcet responsePakcet = new SendToUserResponsePakcet();
        responsePakcet.setVersion(msg.getVersion());
        responsePakcet.setFromUserId(session.getUserId());
        responsePakcet.setFromUserName(session.getUserName());
        responsePakcet.setMsg(msg.getMessage());

        Channel channel = SessionUtil.getChannel(msg.getUserId());

        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(responsePakcet);
        } else {
            System.err.println("[" + msg.getUserId() + "] 不在线，发送失败!");
        }

    }


}
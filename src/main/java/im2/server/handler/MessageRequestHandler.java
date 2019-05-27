package im2.server.handler;

import im2.protocol.request.MessageRequestPacket;
import im2.protocol.response.MessageResponsePacket;
import im2.session.Session;
import im2.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:41
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {

        //拿到消息发送方的session
        Session session = SessionUtil.getSession(ctx.channel());

        //包装消息
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserId(session.getUserId());
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setMessage(packet.getMessage());

        //接收方channel
        Channel channel = SessionUtil.getChannel(packet.getUserId());

        if (channel != null && SessionUtil.hasLogin(channel)) {
            channel.writeAndFlush(responsePacket);
        } else {

        }
    }
}
package im2.server.handler;

import im2.protocol.request.MessageRequestPacket;
import im2.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:41
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息: " + packet.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + packet.getMessage() + "】");

        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
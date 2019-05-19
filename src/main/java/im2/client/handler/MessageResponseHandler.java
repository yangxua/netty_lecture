package im2.client.handler;

import im2.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:40
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + packet.getMessage());
    }
}
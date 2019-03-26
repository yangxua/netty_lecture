package im.client.handler;

import im.protocol.response.SendToGroupResponsePacket;
import im.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/3/26 18:17
 * @Description:
 */
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupResponsePacket msg) throws Exception {
        String fromGroupId = msg.getFromGroupId();
        String fromUser = msg.getFromUserName();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + msg.getMsg());
    }
}
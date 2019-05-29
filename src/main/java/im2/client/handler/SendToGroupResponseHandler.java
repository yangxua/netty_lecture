package im2.client.handler;

import im2.protocol.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/29 21:12
 * @Description:
 */
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupResponsePacket packet) throws Exception {
        String msg = packet.getMsg();
        String userName = packet.getUserName();

        System.out.println("用户【"+ userName +"】发送了一条群消息：" + msg);
    }
}
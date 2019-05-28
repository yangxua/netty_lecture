package im2.client.handler;

import im2.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:37
 * @Description:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket packet) throws Exception {
        if (packet.isSuccess()) {
            System.out.println("退出群聊[" + packet.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + packet.getGroupId() + "]失败！");
        }
    }
}
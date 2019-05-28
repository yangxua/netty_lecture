package im2.client.handler;

import im2.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:24
 * @Description:
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket packet) throws Exception {
        if (packet.isSuccess()) {
            System.out.println("加入群[" + packet.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + packet.getGroupId() + "]失败，原因为：");
        }    }
}
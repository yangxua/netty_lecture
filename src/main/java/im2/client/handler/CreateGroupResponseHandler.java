package im2.client.handler;

import im2.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:43
 * @Description:
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket packet) throws Exception {
        System.out.print("群创建成功，id 为[" + packet.getGroupId() + "], ");
        System.out.println("群里面有：" + packet.getUserNameList());
    }
}
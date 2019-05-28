package im2.client.handler;

import im2.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 21:52
 * @Description:
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket packet) throws Exception {
        System.out.println("群[" + packet.getGroupId() + "]中的人包括：" + packet.getList());
    }
}
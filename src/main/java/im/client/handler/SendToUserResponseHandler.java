package im.client.handler;

import im.protocol.response.SendToUserResponsePakcet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/3/25 20:05
 * @Description:
 */
@ChannelHandler.Sharable
public class SendToUserResponseHandler extends SimpleChannelInboundHandler<SendToUserResponsePakcet> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToUserResponsePakcet msg) throws Exception {
        String fromUserId = msg.getFromUserId();
        String fromUserName = msg.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + msg.getMsg());
    }
}
package xdubbo.core.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xdubbo.model.MessageRequest;

/**
 * @Auther: allanyang
 * @Date: 2019/9/6 15:30
 * @Description:
 */
public class ClientRpcHandler extends SimpleChannelInboundHandler<MessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {

    }
}
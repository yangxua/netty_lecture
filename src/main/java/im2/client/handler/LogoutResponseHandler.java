package im2.client.handler;

import im2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 10:07
 * @Description:
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponseHandler> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponseHandler msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
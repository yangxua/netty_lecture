package im2.server.handler;

import im2.protocol.request.LogoutRequestPacket;
import im2.protocol.response.LogoutResponsePacket;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:54
 * @Description:
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket packet) throws Exception {
        SessionUtil.unBindSession(ctx.channel());

        ctx.channel().writeAndFlush(new LogoutResponsePacket(true));
    }
}
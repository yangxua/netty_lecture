package im2.server.handler;

import im2.protocol.request.LogoutRequestPacket;
import im2.protocol.response.LogoutResponsePacket;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/28 09:54
 * @Description:
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket packet) throws Exception {
        SessionUtil.unBindSession(ctx.channel());

        System.out.println("服务端已解绑，channel: " + ctx.channel());
        ctx.channel().writeAndFlush(new LogoutResponsePacket(true));
    }
}
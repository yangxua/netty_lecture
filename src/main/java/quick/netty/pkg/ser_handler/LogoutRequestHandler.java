package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LogoutRequestPackage;
import quick.netty.pkg.LogoutResponsePackage;
import quick.netty.pkg.session.SessionUtils;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 20:10
 * @Description:
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPackage msg) throws Exception {
        SessionUtils.unBindSession(ctx.channel());

        LogoutResponsePackage responsePackage = new LogoutResponsePackage();
        responsePackage.setSuccess(true);

        ctx.channel().writeAndFlush(responsePackage);
    }
}
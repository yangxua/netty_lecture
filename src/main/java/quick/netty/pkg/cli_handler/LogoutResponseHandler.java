package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LogoutResponsePackage;
import quick.netty.pkg.session.SessionUtils;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 20:07
 * @Description:
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePackage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePackage msg) throws Exception {
        SessionUtils.unBindSession(ctx.channel());
    }
}
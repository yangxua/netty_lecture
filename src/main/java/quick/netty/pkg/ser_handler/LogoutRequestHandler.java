package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPackage> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();
    private LogoutRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPackage msg) throws Exception {
        SessionUtils.unBindSession(ctx.channel());

        LogoutResponsePackage responsePackage = new LogoutResponsePackage();
        responsePackage.setSuccess(true);

        ctx.channel().writeAndFlush(responsePackage);
    }
}
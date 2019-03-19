package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.HeartbeatRequestPackage;
import quick.netty.pkg.HeartbeatResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 20:24
 * @Description:
 */
@ChannelHandler.Sharable
public class HeartbeatRequestHandler extends SimpleChannelInboundHandler<HeartbeatRequestPackage> {

    public static final HeartbeatRequestHandler INSTANCE = new HeartbeatRequestHandler();

    private HeartbeatRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatRequestPackage msg) throws Exception {
        ctx.writeAndFlush(new HeartbeatResponsePackage());
    }
}
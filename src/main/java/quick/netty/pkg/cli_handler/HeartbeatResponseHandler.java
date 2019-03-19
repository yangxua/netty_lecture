package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import quick.netty.pkg.HeartbeatRequestPackage;
import quick.netty.pkg.HeartbeatResponsePackage;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 20:29
 * @Description:
 */
@ChannelHandler.Sharable
public class HeartbeatResponseHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    public static final HeartbeatResponsePackage INSTANCE = new HeartbeatResponsePackage();
    private HeartbeatResponseHandler(){}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        scheduledSendHeartbeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduledSendHeartbeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartbeatRequestPackage());
                scheduledSendHeartbeat(ctx);
            }
        },HEARTBEAT_INTERVAL, TimeUnit.MILLISECONDS);
    }
}
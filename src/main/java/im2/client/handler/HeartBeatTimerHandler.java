package im2.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 14:44
 * @Description:
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEART_BEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        schedule(ctx);

        super.channelActive(ctx);
    }

    private void schedule(ChannelHandlerContext ctx) {
        ctx.executor().schedule(()-> {

            if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(new HeartBeatTimerHandler());
                schedule(ctx);
            }

        }, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
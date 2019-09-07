package sx.rpc.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import sx.rpc.MessageType;
import sx.rpc.struct.Header;
import sx.rpc.struct.NettyMessage;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 17:09
 * @Description:
 */
@Slf4j
public class HeartBeatReqHandler extends SimpleChannelInboundHandler<NettyMessage> {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
        if (msg.getHeader() != null
                && msg.getHeader().getType() == MessageType.LOGIN_RESP
                .value()) {
            heartBeat = ctx.executor().scheduleWithFixedDelay(new HeartBeatReqHandler.HeartBeatTask(ctx), 0 , 5000, TimeUnit.MILLISECONDS);
        } else if (msg.getHeader() != null && msg.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()){
            log.info("Client receive server heart beat message : ---> "
                    + msg);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heartBeat = buildHeatBeat();
            ctx.writeAndFlush(heartBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header  header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            message.setHeader(header);
            return message;
        }
    }
}
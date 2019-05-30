package im2.server.handler;

import im2.protocol.request.HeartBeatRequestPacket;
import im2.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/30 16:25
 * @Description:
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket packet) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
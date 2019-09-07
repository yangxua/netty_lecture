package sx.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import sx.rpc.MessageType;
import sx.rpc.struct.Header;
import sx.rpc.struct.NettyMessage;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 16:50
 * @Description:
 */
@Slf4j
public class HeartBeatRespHandler extends SimpleChannelInboundHandler<NettyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
        if (msg.getHeader() == null && msg.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            ctx.writeAndFlush(buildHeartBeat());
        }
    }

    private NettyMessage buildHeartBeat() {
        NettyMessage msg = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        msg.setHeader(header);
        return msg;
    }
}
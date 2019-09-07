package sx.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import sx.rpc.MessageType;
import sx.rpc.struct.Header;
import sx.rpc.struct.NettyMessage;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 16:22
 * @Description:
 */
@Slf4j
public class LoginAuthRespHandler extends SimpleChannelInboundHandler<NettyMessage> {

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();
    private String[] whiteList = {"127.0.0.1", "129.168.1.104"};


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage msg) throws Exception {
        if (msg.getHeader() != null
                && msg.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            //重复登陆，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte) -1);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean ok = false;
                for (String WIP : whiteList) {
                    if (WIP.equals(ip)) {
                        ok = true;
                        break;
                    }
                }
                loginResp = ok ? buildResponse((byte) 0) : buildResponse((byte) -1);
                if (ok) {
                    nodeCheck.put(nodeIndex, true);
                }
                log.info("The login response is : " + loginResp
                        + " body [" + loginResp.getBody() + "]");
                ctx.writeAndFlush(loginResp);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
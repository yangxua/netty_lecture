package im2.server.handler;

import im2.protocol.request.LoginRequestPacket;
import im2.protocol.response.LoginResponsePacket;
import im2.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:40
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(packet.getVersion());

        if (valid(packet)) {
            responsePacket.setSuccess(true);
            responsePacket.setMsg("登陆成功!");
            LoginUtil.mark(ctx.channel());
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setMsg("用户名或密码错误!");
        }

        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        if (null == loginRequestPacket) {
            return false;
        }
        return "allan".equalsIgnoreCase(loginRequestPacket.getUserName()) && "pwd".equalsIgnoreCase(loginRequestPacket.getPassword());
    }
}
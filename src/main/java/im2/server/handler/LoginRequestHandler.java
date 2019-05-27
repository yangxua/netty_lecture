package im2.server.handler;

import im2.protocol.request.LoginRequestPacket;
import im2.protocol.response.LoginResponsePacket;
import im2.session.Session;
import im2.util.RandomUtil;
import im2.util.SessionUtil;
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
            String userId = RandomUtil.random();
            responsePacket.setUserId(userId);
            responsePacket.setUserName(packet.getUserName());
            SessionUtil.bindSession(new Session(userId, packet.getUserName()), ctx.channel());
            System.out.println("[" + packet.getUserName() + "]登录成功");
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
        return "pwd".equalsIgnoreCase(loginRequestPacket.getPassword());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

}
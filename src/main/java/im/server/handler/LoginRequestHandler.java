package im.server.handler;

import im.protocol.request.LoginRequestPacket;
import im.protocol.response.LoginResponsePacket;
import im.session.Session;
import im.util.IDUtil;
import im.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/22 16:34
 * @Description:
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();
    private LoginRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        String userName = msg.getUserName();
        String password = msg.getPassword();

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setUserName(msg.getUserName());
        responsePacket.setVersion(msg.getVersion());

        if (valid(password)) {
            responsePacket.setSuccess(true);
            String userId = IDUtil.random();
            responsePacket.setUserId(userId);
            System.out.println("[" + responsePacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("密码错误");
            System.out.println(new Date() + ": 登录失败!");
        }

        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(String pwd) {
        return "pwd".equals(pwd);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
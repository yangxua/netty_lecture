package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LoginRequestPackage;
import quick.netty.pkg.LoginResponsePackage;
import quick.netty.pkg.session.Session;
import quick.netty.pkg.session.SessionUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:02
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPackage msg) throws Exception {
        LoginResponsePackage responsePackage = new LoginResponsePackage();
        responsePackage.setUserName(msg.getUsername());
        responsePackage.setVersion(msg.getVersion());

        if (valid(msg)) {
            String userId = randomUuid();
            responsePackage.setUserId(userId);
            responsePackage.setFlag(true);
            System.out.println("[" + msg.getUsername() + "]登录成功");
            SessionUtils.bindSession(new Session(userId, msg.getUsername()), ctx.channel());

        } else {
            responsePackage.setFlag(false);
            responsePackage.setReason("登陆失败");
            System.out.println(new Date() + ": 登录失败!");
            SessionUtils.unBindSession(ctx.channel());
        }


        ctx.channel().writeAndFlush(responsePackage);
    }

    private String randomUuid() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private boolean valid(LoginRequestPackage loginRequestPackage) {
        return true;
    }

}
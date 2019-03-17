package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LoginRequestPackage;
import quick.netty.pkg.LoginResponsePackage;
import quick.netty.pkg.session.Session;
import quick.netty.pkg.session.SessionUtils;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:01
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePackage> {


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePackage msg) throws Exception {
        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if (msg.isFlag()) {
            System.out.println("[" + userName + "]登录成功，userId 为: " + userId);
            SessionUtils.bindSession(new Session(userId,userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因：" + msg.getReason());
        }
    }
}
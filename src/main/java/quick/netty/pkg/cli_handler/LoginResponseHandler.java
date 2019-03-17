package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LoginRequestPackage;
import quick.netty.pkg.LoginResponsePackage;
import quick.netty.pkg.LoginUtils;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:01
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePackage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("client start >>>>>>>>");

        LoginRequestPackage requestPackage = new LoginRequestPackage();
        requestPackage.setUsername("allan");
        requestPackage.setPassword("root");

        ctx.channel().writeAndFlush(requestPackage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePackage msg) throws Exception {
        if (msg.isFlag()) {
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtils.makeAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
        }
    }
}
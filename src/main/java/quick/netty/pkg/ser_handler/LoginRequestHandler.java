package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.LoginRequestPackage;
import quick.netty.pkg.LoginResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:02
 * @Description:
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPackage msg) throws Exception {
        System.out.println("server read>>>>>>>");

        LoginResponsePackage responsePackage = new LoginResponsePackage();
        responsePackage.setVersion(msg.getVersion());
        if (valid(msg)) {
            responsePackage.setFlag(true);
            responsePackage.setReason("登陆成功");
        } else {
            responsePackage.setFlag(false);
            responsePackage.setReason("登陆失败");
        }


        ctx.channel().writeAndFlush(responsePackage);
    }

    private boolean valid(LoginRequestPackage loginRequestPackage) {
        return true;
    }

}
package quick.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import quick.netty.pkg.LoginRequestPackage;
import quick.netty.pkg.LoginResponsePackage;
import quick.netty.pkg.Package;
import quick.netty.pkg.PacketCodeC;

/**
 * @Auther: allanyang
 * @Date: 2019/3/7 20:40
 * @Description:
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("server read>>>>>>>");

        Package requestPackage = PacketCodeC.getInstance().decode((ByteBuf) msg);

        if (requestPackage instanceof LoginRequestPackage) {
            LoginRequestPackage loginRequestPackage= (LoginRequestPackage)requestPackage;

            LoginResponsePackage responsePackage = new LoginResponsePackage();
            responsePackage.setVersion(loginRequestPackage.getVersion());
            if (valid(loginRequestPackage)) {
                responsePackage.setFlag(true);
                responsePackage.setReason("登陆成功");
            } else {
                responsePackage.setFlag(false);
                responsePackage.setReason("登陆失败");
            }

            ByteBuf buf = PacketCodeC.getInstance().encode(ctx.alloc(), responsePackage);

            ctx.channel().writeAndFlush(buf);
        }


    }

    private boolean valid(LoginRequestPackage loginRequestPackage) {
        return false;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
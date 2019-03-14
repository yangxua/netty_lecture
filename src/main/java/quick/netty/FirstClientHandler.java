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
 * @Date: 2019/3/7 20:34
 * @Description:
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("client start >>>>>>>>");

        LoginRequestPackage requestPackage = new LoginRequestPackage();
        requestPackage.setUsername("allan");
        requestPackage.setPassword("root");

        ByteBuf buf = PacketCodeC.getInstance().encode(ctx.alloc(), requestPackage);

        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("client read>>>>");

        Package responsePackage = PacketCodeC.getInstance().decode((ByteBuf) msg);

        if (responsePackage instanceof LoginResponsePackage) {
            LoginResponsePackage loginResponsePackage = (LoginResponsePackage) responsePackage;
            if (loginResponsePackage.isFlag()) {
                System.out.println("登陆成功>>>");
            } else {
                System.out.println("登陆失败，失败原因：" + loginResponsePackage.getReason());
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
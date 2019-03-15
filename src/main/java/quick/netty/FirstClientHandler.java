package quick.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import quick.netty.pkg.*;
import quick.netty.pkg.Package;
import thrift.generated.DataException;

import java.util.Date;

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
                LoginUtils.makeAsLogin(ctx.channel());
                System.out.println("登陆成功>>>");
            } else {
                System.out.println("登陆失败，失败原因：" + loginResponsePackage.getReason());
            }
        } else if (responsePackage instanceof MessageResponsePackage){
            MessageResponsePackage messageResponsePackage = (MessageResponsePackage) responsePackage;

            System.out.println(new Date() + "收到服务端消息：" + messageResponsePackage.getMessage());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
package quick.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import newland.rpc.model.MessageRequest;
import quick.netty.pkg.*;
import quick.netty.pkg.Package;

import java.util.Date;

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
        } else if (requestPackage instanceof MessageRequestPackage) {
            MessageRequestPackage messageRequestPackage = (MessageRequestPackage)requestPackage;

            System.out.println(new Date() + ": 收到客户端消息:" + messageRequestPackage.getMessage());

            MessageResponsePackage responsePackage = new MessageResponsePackage();
            responsePackage.setMessage("[回复客户端消息]：" + messageRequestPackage.getMessage() + "--server answer!");

            ByteBuf buf = PacketCodeC.getInstance().encode(ctx.alloc(), responsePackage);

            ctx.channel().writeAndFlush(buf);
        }


    }

    private boolean valid(LoginRequestPackage loginRequestPackage) {
        return true;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
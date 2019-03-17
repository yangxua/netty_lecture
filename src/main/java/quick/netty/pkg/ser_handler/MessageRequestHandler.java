package quick.netty.pkg.ser_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.MessageRequestPackage;
import quick.netty.pkg.MessageResponsePackage;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:02
 * @Description:
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPackage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPackage msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息:" + msg.getMessage());

        MessageResponsePackage responsePackage = new MessageResponsePackage();
        responsePackage.setMessage("[回复客户端消息]：" + msg.getMessage() + "--server answer!");

        ctx.channel().writeAndFlush(responsePackage);
    }
}
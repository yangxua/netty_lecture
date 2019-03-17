package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.MessageResponsePackage;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/3/17 11:01
 * @Description:
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePackage msg) throws Exception {
        System.out.println(new Date() + "收到服务端消息：" + msg.getMessage());
    }
}
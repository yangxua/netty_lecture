package quick.netty.pkg.ser_handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.MessageRequestPackage;
import quick.netty.pkg.MessageResponsePackage;
import quick.netty.pkg.session.Session;
import quick.netty.pkg.session.SessionUtils;

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

        Session session = SessionUtils.getSession(ctx.channel());

        MessageResponsePackage responsePackage = new MessageResponsePackage();
        responsePackage.setFromUserId(session.getUserId());
        responsePackage.setFromUserName(session.getUserName());
        responsePackage.setMessage(msg.getMessage());

        Channel channel = SessionUtils.getChannel(msg.getToUserId());

        if (channel != null && SessionUtils.hasLogin(channel)) {
            channel.writeAndFlush(responsePackage);
        } else {
            System.err.println("[" + msg.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
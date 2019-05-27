package im2.client.handler;

import im2.protocol.response.LoginResponsePacket;
import im2.session.Session;
import im2.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:39
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.isSuccess()) {
            SessionUtil.bindSession(new Session(packet.getUserId(), packet.getUserName()), ctx.channel());
            System.out.println("[" + packet.getUserName() + "]登录成功，userId 为: " + packet.getUserId());
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + packet.getMsg());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        System.out.println("客户端连接被关闭!");
    }
}
package im2.client.handler;

import im2.protocol.request.LoginRequestPacket;
import im2.protocol.response.LoginResponsePacket;
import im2.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: allanyang
 * @Date: 2019/5/19 12:39
 * @Description:
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");

        //创建对象
        LoginRequestPacket requestPacket = new LoginRequestPacket();
        requestPacket.setUserId(UUID.randomUUID().toString());
        requestPacket.setUserName("allan");
        requestPacket.setPassword("pwd");

        //写数据
        ctx.channel().writeAndFlush(requestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket packet) throws Exception {
        if (packet.isSuccess()) {
            LoginUtil.mark(ctx.channel());
            System.out.println(new Date() + ": 客户端登录成功");
        } else {
            System.out.println(new Date() + ": 客户端登录失败，原因：" + packet.getMsg());
        }
    }
}
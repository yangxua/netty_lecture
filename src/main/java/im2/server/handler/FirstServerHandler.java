package im2.server.handler;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/5/16 21:55
 * @Description:
 */
public class FirstServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(new Date() + ": 服务端读到数据 -> " + msg);


        ByteBuf buf = getBuf(ctx);
        ctx.channel().writeAndFlush(buf);

    }

    private ByteBuf getBuf(ChannelHandlerContext ctx) {

        byte[] bytes = "欢迎欢迎！".getBytes(Charsets.UTF_8);

        ByteBuf buffer = ctx.alloc().buffer();

        buffer.writeBytes(bytes);

        return buffer;
    }
}
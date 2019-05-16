package im2.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Auther: allanyang
 * @Date: 2019/5/16 21:48
 * @Description:
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");

        ByteBuf buf = getBuf(ctx);

        ctx.channel().writeAndFlush(buf);
    }

    private ByteBuf getBuf(ChannelHandlerContext ctx) {

        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "hello，闪电侠".getBytes(Charset.forName("utf-8"));

        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;

        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

    }
}
package sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/1/29 16:41
 * @Description:
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getId());
        System.out.println(msg.getAddress());
    }
}
package sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: allanyang
 * @Date: 2019/1/29 16:44
 * @Description:
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Student student = MyDataInfo.Student.newBuilder().setName("ZS").setId(1).setAddress("北京").build();
        ctx.channel().writeAndFlush(student);
    }

}
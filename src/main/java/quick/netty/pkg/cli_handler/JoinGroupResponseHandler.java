package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.JoinGroupResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:33
 * @Description:
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePackage msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("加入群[" + msg.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
        }
    }
}
package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.QuitGroupResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/18 21:45
 * @Description:
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePackage msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("退出群聊[" + msg.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + msg.getGroupId() + "]失败！");
        }

    }
}
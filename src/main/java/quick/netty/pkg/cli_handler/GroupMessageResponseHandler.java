package quick.netty.pkg.cli_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import quick.netty.pkg.GroupMessageResponsePackage;

/**
 * @Auther: allanyang
 * @Date: 2019/3/19 18:15
 * @Description:
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePackage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePackage msg) throws Exception {
        String fromGroupId = msg.getGroupId();
        String fromUser = msg.getUserName();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + msg.getMessage());
    }
}